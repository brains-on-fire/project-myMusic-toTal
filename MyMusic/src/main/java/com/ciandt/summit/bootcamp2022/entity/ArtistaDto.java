package com.ciandt.summit.bootcamp2022.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Artistas")
public class ArtistaDto {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String nome;

    @OneToMany(mappedBy = "artista")
    @JsonIgnore
    @ToString.Exclude
    List<MusicaDto> musicas;
}
