package com.ciandt.summit.bootcamp2022.dto;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaNaoEncontradaException;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicaDTO {

    @NotNull
    private List<Music> data = new ArrayList<>();

    public void addMusica(Music music) {
        if (music == null)
            throw new MusicaNaoEncontradaException();

        this.data.add(music);
    }
}
