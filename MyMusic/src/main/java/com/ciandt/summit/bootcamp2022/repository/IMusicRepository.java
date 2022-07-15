package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.MusicaEntity;

import java.util.List;

public interface IMusicRepository {
    List<MusicaEntity> buscarMusicas(String nome);
}
