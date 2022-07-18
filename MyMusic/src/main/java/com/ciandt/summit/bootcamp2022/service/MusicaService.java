package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.IMusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    @Autowired
    private IMusicaRepository musicaRepository;

    public Optional<List<Musica>> findByNameArtistOrMusic(String filtro) {

        List<Musica> queryResult = musicaRepository.buscarMusicas(filtro);

        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(queryResult);
    }
}
