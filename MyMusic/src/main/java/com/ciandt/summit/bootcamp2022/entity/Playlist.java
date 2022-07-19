package com.ciandt.summit.bootcamp2022.entity;

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
@Table(name = "Playlist")
public class Playlist {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany
    @JoinColumn(name = "PlaylistMusicas")
    private List<Musica> musicas;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist playlist = (Playlist) o;
        return id != null && Objects.equals(id, playlist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
