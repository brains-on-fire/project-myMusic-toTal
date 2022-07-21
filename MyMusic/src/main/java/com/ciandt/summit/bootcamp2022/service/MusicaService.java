package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {
    @Autowired
    MusicaRepository musicaRepository;

    @Cacheable(value = "ten-minutes-cache", key = "'QueryCache'+#filtro")
    public Optional<MusicaDTO> findByNameArtistOrMusic(String filtro) {

        List<Musica> queryResult = musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro);

        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new MusicaDTO(queryResult));
    }
}
