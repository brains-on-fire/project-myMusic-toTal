package com.ciandt.summit.bootcamp2022.controller;
import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
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

        Artist artistAdicionar = Artist.builder().id("1").nome("Artista Teste").build();
        Music musicAdicionar = Music.builder().id("1").nome("Musica Teste").artist(artistAdicionar).build();

        List<Music> listaMusicAdicionar = new ArrayList<>();
        listaMusicAdicionar.add(musicAdicionar);

        MusicaDTO musicaDTOAdicionar = new MusicaDTO(listaMusicAdicionar);

        String json = new ObjectMapper().writeValueAsString(musicaDTOAdicionar);
        Playlist playlist = Playlist.builder().id("1").music(listaMusicAdicionar).build();

        MusicaDTO musicaDTOAdicionada = MusicaDTO.builder().data(musicaDTOAdicionar.getData()).build();

        when(playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88","ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTOAdicionar)).thenReturn(Optional.of(musicaDTOAdicionada));

        this.mockMvc.perform(
                        post("/playlists/1/1/musicas")
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


        when(playlistService.addMusicaToPlaylist("0c2a04a5-d8d2-42a2-a90f-3d6e8f912b88", "ed7f6acb-1aad-42c9-8c7b-5a49540fcbc4", musicaDTO)).thenThrow(new PayloadInvalidoException());

        this.mockMvc.perform(
                        post("/playlists/1/1/musicas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
