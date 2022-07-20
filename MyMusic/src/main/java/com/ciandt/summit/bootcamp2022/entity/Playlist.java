package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Playlists")
public class Playlist {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PlaylistMusicas",
            joinColumns = {@JoinColumn(name = "PlaylistId")},
            inverseJoinColumns = {@JoinColumn(name = "MusicaId")}
    )
    private List<Musica> musicas = new ArrayList<>();

}
