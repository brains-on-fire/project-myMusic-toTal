package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MusicServiceTest {

    @InjectMocks
    private MusicaService musicaService;

    @Mock
    private MusicaRepository musicaRepository;

    @Test
    @DisplayName("Deve retornar lista de músicas com o filtro THE")
    public void deveRetornarListaDeMusicaComFiltroInformado() {

        String filtro = "THE";

        Artist artist = Artist.builder().id("1").nome("Teste Artista").build();
        Music music = Music.builder().id("1").nome("Teste Musica").artist(artist).build();
        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);

        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();

        when(musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro)).thenReturn(listaMusic);

        Optional<MusicaDTO> result = musicaService.findByNameArtistOrMusic(filtro);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Deve retornar lista vazia com filtro ABCDEF")
    public void deveRetornarListaDeMusicaComFiltroAbcdef() {

        String filtro = "ABCDEF";

        Artist artist = Artist.builder().id("1").nome("Teste Artista").build();
        Music music = Music.builder().id("1").nome("Teste Musica").artist(artist).build();
        List<Music> listaMusic = new ArrayList<>();
        listaMusic.add(music);

        MusicaDTO musicaDTO = MusicaDTO.builder().data(listaMusic).build();

        when(musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro)).thenReturn(new ArrayList<>());

        Optional<MusicaDTO> result = musicaService.findByNameArtistOrMusic(filtro);

        Assertions.assertFalse(result.isPresent());
    }


}
