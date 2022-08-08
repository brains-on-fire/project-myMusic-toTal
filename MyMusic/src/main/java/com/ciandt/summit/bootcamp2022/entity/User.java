package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    @NotNull
    private String nome;

    @ManyToOne
    @JoinColumn(name = "PlaylistId")
    private Playlist playlistId;

    @ManyToOne
    @JoinColumn(name = "TipoUsuario")
    private UserType userTypeId;

}
