package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
public class MusicaEntity {

    private String id;
    private String nome;
    @ManyToOne
    private ArtistaEntity artista;

    public MusicaEntity(String id, String nome, ArtistaEntity artista) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
    }
}
