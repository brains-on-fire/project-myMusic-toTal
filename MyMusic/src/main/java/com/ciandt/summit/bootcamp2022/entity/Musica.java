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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return id.equals(musica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
