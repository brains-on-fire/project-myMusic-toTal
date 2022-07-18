package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.MusicaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaJpaRepository extends JpaRepository<MusicaDto, String> {
    List<MusicaDto> findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(String nome, String nome1);
}
