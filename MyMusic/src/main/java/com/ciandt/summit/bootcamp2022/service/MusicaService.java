package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {
    @Autowired
    MusicaRepository musicaRepository;

    @Cacheable(value = "ten-second-cache", key = "'QueryCache'+#filtro")
    public Optional<List<Musica>> findByNameArtistOrMusic(String filtro) {

        List<Musica> queryResult = musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro);

        if (queryResult.isEmpty()) {
            return Optional.empty();
        }


        return Optional.of(queryResult);
    }
}
