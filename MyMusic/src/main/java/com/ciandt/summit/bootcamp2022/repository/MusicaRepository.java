package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends JpaRepository<Music, String> {
    List<Music> findByNomeContainsIgnoreCaseOrArtist_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtist_NomeAscNomeAsc(String nome, String nome1);
}
