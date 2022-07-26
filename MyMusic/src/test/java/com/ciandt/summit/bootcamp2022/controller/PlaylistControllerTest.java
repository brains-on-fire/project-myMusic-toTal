package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaJaCadastradaNaPlaylistException;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaNaoEncontradaException;
import com.ciandt.summit.bootcamp2022.exceptions.PlaylistNaoEncontrada;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlaylistControllerTest {
    @InjectMocks
    private PlaylistController playlistController;

    @Mock
    private PlaylistService playlistService;

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistController playlistControllerDatabase;

    @Autowired
    private PlaylistRepository playlistRepositoryDatabase;

    @Autowired
    private MusicaRepository musicaRepositoryDatabase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Busca todas as playlists e suas músicas")
    void deveRetornarStatusOkAoListaTodasPlaylists() {
        ResponseEntity<Object> response = playlistControllerDatabase.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Busca todas as músicas de uma Playlist existente")
    void deveRetornarOkAoListarMusicasDeUmaPlaylist() {
        ResponseEntity<Object> response = playlistControllerDatabase.findMusicasByPlaylistId("fadad621-3ff5-4c66-94ba-f57cc16df792");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Busca todas as músicas de uma Playlist inexistente")
    void deveRetornarExceptionAoListarMusicasDeUmaPlaylistInexistente() {
        Assertions.assertThrows(PlaylistNaoEncontrada.class, () -> playlistControllerDatabase.findMusicasByPlaylistId("abc"));
    }

    @Test
    @DisplayName("Busca Playlist inexistente")
    void deveRetornarListaVaziaAoPesquisarPlayListInvalida() {
        Optional<Playlist> playlist = playlistRepositoryDatabase.findById("abc");
        Assertions.assertTrue(playlist.isEmpty());
    }

    @Test
    @DisplayName("Adicionar uma música na Playlist")
    void deveAdicionarMusicaERetornarStatusCreated() {
        Artista artista = new Artista();
        artista.setNome("Artista Teste");

        Musica musica = new Musica();
        musica.setNome("Musica Teste");
        musica.setArtista(artista);

        List<Musica> listaMusica = new ArrayList<>();
        listaMusica.add(musica);

        MusicaDTO musicaDTO = new MusicaDTO(listaMusica);

        when(playlistService.addMusicaToPlaylist("1234", musicaDTO)).thenReturn(Optional.of(musicaDTO));

        ResponseEntity<Object> response = playlistController.addMusicaToPlaylist("1234", musicaDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Adicionar uma música já existente Playlist")
    void deveRetornarExceptionAoCadastrarMusicaJaExistente() {
        Optional<Musica> musica = musicaRepositoryDatabase.findById("3dea32bb-0241-4500-9358-82eb98d82bbc");

        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.addMusica(musica.get());

        when(playlistController.addMusicaToPlaylist("fadad621-3ff5-4c66-94ba-f57cc16df792", musicaDTO)).thenThrow(MusicaJaCadastradaNaPlaylistException.class);

        Assertions.assertThrows(MusicaJaCadastradaNaPlaylistException.class, () -> playlistController.addMusicaToPlaylist("fadad621-3ff5-4c66-94ba-f57cc16df792", musicaDTO));
    }

    @Test
    @DisplayName("Adicionar uma música em Playlist Inválida")
    void deveRetornarExceptionAoCadastrarMusicaEmPlaylistInvalida() {
        Optional<Musica> musica = musicaRepositoryDatabase.findById("3dea32bb-0241-4500-9358-82eb98d82bbc");

        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.addMusica(musica.get());

        Assertions.assertThrows(PlaylistNaoEncontrada.class, () -> playlistControllerDatabase.addMusicaToPlaylist("abc", musicaDTO));
    }

    @Test
    @DisplayName("Adicionar Música inválida a uma Playlist")
    void deveRetornarExceptionAoCadastrarMusicaInvalidaEmPlaylist() {
        Optional<Musica> musica = musicaRepositoryDatabase.findById("abc");

        MusicaDTO musicaDTO = new MusicaDTO();
        Musica musicaAdicionar = musica.isEmpty() ? null : musica.get();

        Assertions.assertThrows(MusicaNaoEncontradaException.class, () -> musicaDTO.addMusica(musicaAdicionar));
    }

    @Test
    @DisplayName("Remover com sucesso música da playlist")
    void deveRemoverMusicaDaPlaylist(){
        ResponseEntity<Object> respose = playlistController.removeMusicaFromPlaylist("a24dd5fb-fefd-4466-b246-d447b73c7ab9", "7d6efa1b-453d-4f39-86bd-ab166cd5ea9d");
        assertEquals(HttpStatus.NO_CONTENT, respose.getStatusCode());
    }

    @Test
    @DisplayName("Remover música de uma playlist inexistente")
    void deveRetornarPlaylistNaoEncontrada(){
        when(playlistController.removeMusicaFromPlaylist("xxsdsffg", "7d6efa1b-453d-4f39-86bd-ab166cd5ea9d")).thenThrow(PlaylistNaoEncontrada.class);
        Assertions.assertThrows(PlaylistNaoEncontrada.class, () -> playlistController.removeMusicaFromPlaylist("xxsdsffg", "7d6efa1b-453d-4f39-86bd-ab166cd5ea9d"));
    }

    @Test
    @DisplayName("Remover música inexistente de uma playlist existente")
    void deveRetornarMusicaNaoEncontrada(){
        when(playlistController.removeMusicaFromPlaylist("a24dd5fb-fefd-4466-b246-d447b73c7ab9", "dssdfsddf")).thenThrow(MusicaNaoEncontradaException.class);
        Assertions.assertThrows(MusicaNaoEncontradaException.class, () -> playlistController.removeMusicaFromPlaylist("a24dd5fb-fefd-4466-b246-d447b73c7ab9", "dssdfsddf"));
    }


}