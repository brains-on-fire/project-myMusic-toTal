package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MusicaRepository extends JpaRepository<Musica, String> {
    @Query(value = "SELECT m FROM Musica m LEFT JOIN Artista a ON a.id = m.artista.id WHERE Upper(a.nome) Like Upper(concat('%', :nome,'%')) " +
            "OR Upper(m.nome) like Upper(concat('%', :nome,'%')) ORDER BY a.nome, m.nome")
    List<Musica> findAllByMusicOrArtist(@Param("nome") String nome);
}
