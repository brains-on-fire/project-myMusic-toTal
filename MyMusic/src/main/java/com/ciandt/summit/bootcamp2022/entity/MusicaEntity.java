package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class MusicaEntity {
    private String id;
    private String nome;
    private ArtistaEntity artista;
}
