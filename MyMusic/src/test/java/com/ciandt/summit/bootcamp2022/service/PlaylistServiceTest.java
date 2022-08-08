package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaJaCadastradaNaPlaylistException;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaNaoEncontradaException;
import com.ciandt.summit.bootcamp2022.exceptions.PayloadInvalidoException;
import com.ciandt.summit.bootcamp2022.exceptions.PlaylistNaoEncontrada;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve adicionar uma música na playlist")
    public void deveAdicionarUmaMusicaNaPlaylist() {
        Artist artist = Artist.builder().id("1").nome("Artista Teste").build();
        Music music = Music.builder().id("1").nome("Musica Teste").artist(artist).build();
        Playlist playlist = Playlist.builder().id("1").music(new ArrayList<>()).build();

        User user = new User();
        UserType userType = UserType.builder().id("2").descricao("premium").build();
        user.setUserTypeId(userType);
        user.setPlaylistId(playlist);
        user.setId("1");
        user.setNome("User Test");

        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(user));

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(music));

        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenReturn(playlist);

        Optional<MusicaDTO> playlistSalva = playlistService.addMusicaToPlaylist("1", "1", musicaDTO);

        Assertions.assertEquals(playlistSalva.get().getData().get(0).getId(), "1");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getNome(), "Musica Teste");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getArtist().getId(), "1");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getArtist().getNome(), "Artista Teste");

    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música com Payload inválido")
    public void deveRetornarExcecaoAoAdicionarMusicaComPayloadInvalido() {
        Artist artist = Artist.builder().id("1").build();
        Music music = Music.builder().id("1").nome("Musica Teste").artist(artist).build();

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(music));

        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();


        Playlist playlist = Playlist.builder().id("1").music(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenThrow(new PayloadInvalidoException());

        assertThrows(PayloadInvalidoException.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }

    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música não existente")
    public void deveRetornarExcecaoAoAdicionarMusicaNaoCadastrada() {
        Artist artist = Artist.builder().id("1").nome("Artista Teste").build();
        Music music = Music.builder().id("1").nome("Musica Teste").artist(artist).build();

        when(musicaRepository.findById(Mockito.anyString())).thenThrow(new MusicaNaoEncontradaException());

        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();


        Playlist playlist = Playlist.builder().id("1").music(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenThrow(new PayloadInvalidoException());

        assertThrows(MusicaNaoEncontradaException.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música a uma playlist não existente")
    public void deveRetornarExcecaoAoAdicionarMusicaAUmaPlaylistNaoExistente() {
        Artist artist = Artist.builder().id("1").nome("Artista Teste").build();
        Music music = Music.builder().id("1").nome("Musica Teste").artist(artist).build();

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(music));

        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();


        Playlist playlist = Playlist.builder().id("1").music(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenThrow(new PlaylistNaoEncontrada());

        when(playlistRepository.save(playlist)).thenThrow(new PlaylistNaoEncontrada());

        assertThrows(PlaylistNaoEncontrada.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música já existente")
    public void deveRetornarExcecaoAoAdicionarMusicaJaExistente() {

        Artist artist = Artist.builder().id("1").nome("Artista Teste").build();
        Music music = Music.builder().id("1").nome("Musica Teste").artist(artist).build();

        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);

        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();
        Playlist playlist = Playlist.builder().id("1").music(listaMusic).build();
        UserType userTypePremium = UserType.builder().id("2").descricao("premium").build();
        User user = new User();
        user.setUserTypeId(userTypePremium);
        user.setPlaylistId(playlist);
        user.setId("1");
        user.setNome("User Test");

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(user));
        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(music));
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));
        when(playlistRepository.save(playlist)).thenThrow(new MusicaJaCadastradaNaPlaylistException());

        assertThrows(MusicaJaCadastradaNaPlaylistException.class, () -> playlistService.addMusicaToPlaylist("1","1", musicaDTO));
    }


}
