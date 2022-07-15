package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.ArtistaEntity;
import com.ciandt.summit.bootcamp2022.entity.MusicaEntity;
import com.ciandt.summit.bootcamp2022.repository.dto.Artista;
import com.ciandt.summit.bootcamp2022.repository.dto.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicRepository implements IMusicRepository {

    @Autowired
    private MusicaRepository musicaRepository;

    @Override
    public List<MusicaEntity> buscarMusicas(String nome) {
        List<MusicaEntity> musicasEntity = new ArrayList<MusicaEntity>();
        List<Musica> musicas = musicaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(nome, nome);

        for(Musica item : musicas) {
            Artista artista = item.getArtista();
            musicasEntity.add(new MusicaEntity(item.getId(), item.getNome(), new ArtistaEntity(artista.getId(), artista.getNome())));
        }

        return musicasEntity;
    }
}