package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.exceptions.PayloadInvalidoException;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class PlaylistControllerTest {
    @MockBean
    private PlaylistService playlistService;

    @MockBean
    private PlaylistRepository playlistRepository;

    @MockBean
    private MusicaRepository musicaRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve adicionar música válida e retornar CREATED")
    public void deveAdicionarMusicaERetornarCreated() throws Exception {

        Artista artistaAdicionar = Artista.builder().id("1").nome("Artista Teste").build();
        Musica musicaAdicionar = Musica.builder().id("1").nome("Musica Teste").artista(artistaAdicionar).build();

        List<Musica> listaMusicaAdicionar = new ArrayList<>();
        listaMusicaAdicionar.add(musicaAdicionar);

        MusicaDTO musicaDTOAdicionar = new MusicaDTO(listaMusicaAdicionar);

        String json = new ObjectMapper().writeValueAsString(musicaDTOAdicionar);

        MusicaDTO musicaDTOAdicionada = MusicaDTO.builder().data(musicaDTOAdicionar.getData()).build();

        when(playlistService.addMusicaToPlaylist("1", musicaDTOAdicionar)).thenReturn(Optional.of(musicaDTOAdicionada));

        this.mockMvc.perform(
                        post("/playlists/1/musicas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Deve remover música e retornar NO CONTENT")
    public void deveRemoverMusicaERetornarNoContent() throws Exception {

        this.mockMvc.perform(
                        delete("/playlists/1/musicas/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(playlistService, times(1)).removeMusicaFromPlaylist("1", "1");
    }

    @Test
    @DisplayName("Deve retornar Exception Payload Inválido")
    public void deveRetornarPayloadInvalido() throws Exception {

        MusicaDTO musicaDTO = new MusicaDTO(null);

        String json = new ObjectMapper().writeValueAsString(musicaDTO);

        when(playlistService.addMusicaToPlaylist("1", musicaDTO)).thenThrow(new PayloadInvalidoException());

        this.mockMvc.perform(
                        post("/playlists/1/musicas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
