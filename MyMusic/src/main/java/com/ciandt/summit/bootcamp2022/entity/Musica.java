package com.ciandt.summit.bootcamp2022.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Musica {
    private String id;
    private String nome;
    private Artista artista;
}
