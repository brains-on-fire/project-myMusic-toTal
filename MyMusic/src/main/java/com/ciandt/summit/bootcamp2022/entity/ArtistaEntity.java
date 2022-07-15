package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

@Getter
@Setter
public class ArtistaEntity {

    private String id;
    private String nome;

    public ArtistaEntity(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
