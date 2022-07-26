package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.controller.PlaylistController;
import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlaylistServiceTest {

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
    @DisplayName("Deletar música em uma Playlist")
    void deveDeletarMusicaEmUmaPlaylist() {
        playlistService.removeMusicaFromPlaylist("abc", "def");
        Optional<Musica> musica = musicaRepositoryDatabase.findById("abc");
        Assertions.assertFalse(musica.isPresent());
    }
}
