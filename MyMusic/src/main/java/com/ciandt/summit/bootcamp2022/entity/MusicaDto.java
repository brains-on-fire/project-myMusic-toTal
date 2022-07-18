package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Musicas")
public class MusicaDto {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ArtistaId")
    private ArtistaDto artista;
}
