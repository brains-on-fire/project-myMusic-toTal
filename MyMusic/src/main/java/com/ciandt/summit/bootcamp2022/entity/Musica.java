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
public class Musica {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ArtistaId")
    private Artista artista;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Musica musica = (Musica) o;
        return id != null && Objects.equals(id, musica.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
