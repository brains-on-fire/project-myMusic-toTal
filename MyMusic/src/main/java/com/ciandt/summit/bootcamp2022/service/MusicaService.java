package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {
    @Autowired
    MusicaRepository musicaRepository;

    public List<Musica> findByNameArtistOrMusic(String filtro){
      return  musicaRepository.findAllByMusicOrArtist(filtro);
    }
    public List<Musica> findAll(){
        return  musicaRepository.findAll();
    }
}
