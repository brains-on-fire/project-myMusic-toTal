package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.MusicaEntity;

import java.util.List;

public interface IMusicaService {

    List<MusicaEntity> findByNameArtistOrMusic(String filtro);
}
