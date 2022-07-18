package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Musica;

import java.util.List;

public interface IMusicaRepository {
    List<Musica> buscarMusicas(String filtro);
}