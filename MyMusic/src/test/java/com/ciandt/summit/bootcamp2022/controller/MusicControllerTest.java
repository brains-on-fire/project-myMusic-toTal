package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class MusicControllerTest {
    @MockBean
    private MusicaService musicaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Busca >= 3 caracteres deve retornar OK")
    public void deveRetornarOkAoPesquisarComMaisDeDoisCaracteres() throws Exception {
        List<Music> music = new ArrayList<>();
        music.add(new Music("123", "Musica Teste", new Artist("1", "Artista Teste")));
        MusicaDTO musicaDTO = new MusicaDTO(music);

        Mockito.when(musicaService.findByNameArtistOrMusic("Teste")).thenReturn(Optional.of(musicaDTO));

        this.mockMvc.perform(get("/musicas?filtro=Teste"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Busca < 3 caracteres deve retornar BAD REQUEST")
    public void deveRetornarBadRequestAoPesquisarComMenosDeTresCaracteres() throws Exception {
        this.mockMvc.perform(get("/musicas?filtro=Te"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Busca sem parâmetro deve retornar BAD REQUEST")
    public void deveRetornarBadRequestAoPesquisarSemParametro() throws Exception {

        this.mockMvc.perform(get("/musicas"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Busca sem resultados deve retornar NO CONTENT")
    public void deveRetornarNoContentAoRetornarResultadoDaPesquisaVazio() throws Exception {

        this.mockMvc.perform(get("/musicas?filtro=TesteTesteTeste"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @DisplayName("Busca >= 3 caracteres INSENSITIVOS deve retornar NO CONTENT")
    public void deveRetornarNoContentAoPesquisarComMaisDeDoisCaracteresInsensitivos() throws Exception {
        List<Music> music = new ArrayList<>();
        music.add(new Music("123", "Musica Teste", new Artist("1", "Artista Teste")));
        MusicaDTO musicaDTO = new MusicaDTO(music);

        Mockito.when(musicaService.findByNameArtistOrMusic("Teste")).thenReturn(Optional.of(musicaDTO));

        this.mockMvc.perform(get("/musicas?filtro=TESTE"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
