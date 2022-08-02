package com.ciandt.summit.bootcamp2022.dto;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
    private String id;
    private final List<Musica> musicas = new ArrayList<>();
}
