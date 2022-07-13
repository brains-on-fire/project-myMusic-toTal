package com.ciandt.summit.bootcamp2022.entity;
import lombok.Data;

import javax.persistence.*;

    @Entity
    @Table(name = "Musicas")
    @Data
    public class Musica {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "Nome")
    private String nome;

    private String artistaId;

    @ManyToOne
    @JoinColumn(name = "ArtistaId")
    private Artista artista;
    }
