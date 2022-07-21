package com.ciandt.summit.bootcamp2022.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Artistas")
public class Artista {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private String id;

    @Column(name = "Nome")
    @NotNull
    private String nome;

    @OneToMany(mappedBy = "artista")
    @JsonIgnore
    @ToString.Exclude
    List<Musica> musicas = new ArrayList<>();
}
