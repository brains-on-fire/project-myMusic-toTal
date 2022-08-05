package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.dto.UsuarioDTO;
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
        Artista artista = Artista.builder().id("1").nome("Artista Teste").build();
        Musica musica = Musica.builder().id("1").nome("Musica Teste").artista(artista).build();
        Playlist playlist = Playlist.builder().id("1").musicas(new ArrayList<>()).build();

        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuario = TipoUsuario.builder().id("2").descricao("premium").build();
        usuario.setTipoUsuarioId(tipoUsuario);
        usuario.setPlaylistId(playlist);
        usuario.setId("1");
        usuario.setNome("User Test");

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusica).build();

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(musica));

        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenReturn(playlist);

        Optional<MusicaDTO> playlistSalva = playlistService.addMusicaToPlaylist("1", "1", musicaDTO);

        Assertions.assertEquals(playlistSalva.get().getData().get(0).getId(), "1");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getNome(), "Musica Teste");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getArtista().getId(), "1");
        Assertions.assertEquals(playlistSalva.get().getData().get(0).getArtista().getNome(), "Artista Teste");

    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música com Payload inválido")
    public void deveRetornarExcecaoAoAdicionarMusicaComPayloadInvalido() {
        Artista artista = Artista.builder().id("1").build();
        Musica musica = Musica.builder().id("1").nome("Musica Teste").artista(artista).build();

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(musica));

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusica).build();


        Playlist playlist = Playlist.builder().id("1").musicas(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenThrow(new PayloadInvalidoException());

        assertThrows(PayloadInvalidoException.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }

    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música não existente")
    public void deveRetornarExcecaoAoAdicionarMusicaNaoCadastrada() {
        Artista artista = Artista.builder().id("1").nome("Artista Teste").build();
        Musica musica = Musica.builder().id("1").nome("Musica Teste").artista(artista).build();

        when(musicaRepository.findById(Mockito.anyString())).thenThrow(new MusicaNaoEncontradaException());

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusica).build();


        Playlist playlist = Playlist.builder().id("1").musicas(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));

        when(playlistRepository.save(playlist)).thenThrow(new PayloadInvalidoException());

        assertThrows(MusicaNaoEncontradaException.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música a uma playlist não existente")
    public void deveRetornarExcecaoAoAdicionarMusicaAUmaPlaylistNaoExistente() {
        Artista artista = Artista.builder().id("1").nome("Artista Teste").build();
        Musica musica = Musica.builder().id("1").nome("Musica Teste").artista(artista).build();

        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(musica));

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);
        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusica).build();


        Playlist playlist = Playlist.builder().id("1").musicas(new ArrayList<>()).build();
        when(playlistRepository.findById("1")).thenThrow(new PlaylistNaoEncontrada());

        when(playlistRepository.save(playlist)).thenThrow(new PlaylistNaoEncontrada());

        assertThrows(PlaylistNaoEncontrada.class, () -> playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO));
    }


    @Test
    @DisplayName("Deve retornar erro ao adicionar uma música já existente")
    public void deveRetornarExcecaoAoAdicionarMusicaJaExistente() {

        Artista artista = Artista.builder().id("1").nome("Artista Teste").build();
        Musica musica = Musica.builder().id("1").nome("Musica Teste").artista(artista).build();

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);

        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusica).build();

        Playlist playlist = Playlist.builder().id("1").musicas(listaMusica).build();

        TipoUsuario tipoUsuarioPremium = TipoUsuario.builder().id("2").descricao("premium").build();

        Usuario usuario = new Usuario();
        usuario.setTipoUsuarioId(tipoUsuarioPremium);
        usuario.setPlaylistId(playlist);
        usuario.setId("1");
        usuario.setNome("User Test");


        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(musicaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(musica));
        when(playlistRepository.findById("1")).thenReturn(Optional.of(playlist));
        when(playlistRepository.save(playlist)).thenThrow(new MusicaJaCadastradaNaPlaylistException());

        assertThrows(MusicaJaCadastradaNaPlaylistException.class, () -> playlistService.addMusicaToPlaylist("1","1", musicaDTO));
    }


}
