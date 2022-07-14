package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    MusicaRepository musicaRepository;

    public ResponseEntity<Object> findByNameArtistOrMusic(String filtro) {

        if (filtro.length() < 3) {
            return ResponseHandler.generateResponse("Filtro necessita ter mais de 3 caracteres",
                    HttpStatus.BAD_REQUEST);
        }

        List<Musica> queryResult = musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro);

        if (queryResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseHandler.generateResponse(String.format("%d resultados encontrados", queryResult.size()),
                HttpStatus.OK, queryResult);
    }
}
