package com.ciandt.summit.bootcamp2022.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Artistas")
@Data
public class Artista {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String nome;

    @OneToMany(mappedBy = "artist")
    List<Musica> musicas;

}
