package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.ArtistaDto;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.MusicaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicaRepository implements IMusicaRepository {

    @Autowired
    private MusicaJpaRepository musicaJpaRepository;

    @Override
    public List<Musica> buscarMusicas(String filtro) {
        List<Musica> musicas = new ArrayList<Musica>();
        List<MusicaDto> musicasDto = musicaJpaRepository.findByNomeContainsIgnoreCaseOrArtista_NomeContainsIgnoreCaseAllIgnoreCaseOrderByArtista_NomeAscNomeAsc(filtro, filtro);

        for(MusicaDto item : musicasDto) {
            ArtistaDto artistaDto = item.getArtista();
            musicas.add(new Musica(
                    item.getId(),
                    item.getNome(),
                    new Artista(artistaDto.getId(), artistaDto.getNome())));
        }

        return musicas;
    }
}
