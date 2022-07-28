package com.ciandt.summit.bootcamp2022.dto;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaNaoEncontradaException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicaDTO {

    private List<Musica> data = new ArrayList<>();

    public void addMusica(Musica musica) {
        if (musica == null)
            throw new MusicaNaoEncontradaException();

        this.data.add(musica);
    }
}
