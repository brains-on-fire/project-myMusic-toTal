package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Musicas")
public class Musica {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private String id;

    @Column(name = "Nome")
    @NotNull
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ArtistaId")
    @NotNull
    private Artista artista;
}
