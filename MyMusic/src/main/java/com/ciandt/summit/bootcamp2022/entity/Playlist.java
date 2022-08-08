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
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Playlists")
public class Playlist {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PlaylistMusicas", joinColumns = {@JoinColumn(name = "PlaylistId")}, inverseJoinColumns = {@JoinColumn(name = "MusicaId")})
    private List<Music> music = new ArrayList<>();

    public void addMusica(Music music) {
        if (music != null)
            this.music.add(music);
    }

    public void removeMusica(Music music){
        this.music.remove(music);
    }

    public Integer countMusica(Playlist playlist){
        return Math.toIntExact(playlist.music.stream().count());
    }

}
