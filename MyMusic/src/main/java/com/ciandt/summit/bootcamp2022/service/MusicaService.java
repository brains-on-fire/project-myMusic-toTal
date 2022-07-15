package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.MusicaEntity;
import com.ciandt.summit.bootcamp2022.repository.IMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService implements IMusicaService{

    @Autowired
    private IMusicRepository musicaRepository;

    @Override
    public List<MusicaEntity> findByNameArtistOrMusic(String filtro) {

        List<MusicaEntity> queryResult = musicaRepository.buscarMusicas(filtro);

        return queryResult;
    }
}
