package com.ciandt.summit.bootcamp2022.repository.dto;

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
public class Artista {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String nome;

    @OneToMany(mappedBy = "artista")
    @JsonIgnore
    @ToString.Exclude
    List<Musica> musicas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artista artista = (Artista) o;
        return id != null && Objects.equals(id, artista.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}