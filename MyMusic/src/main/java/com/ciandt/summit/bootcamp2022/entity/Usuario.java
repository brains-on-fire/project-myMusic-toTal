package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    @NotNull
    private String nome;

    // ManyToOne or OneToOne > ManyToMany
    @ManyToOne
    @JoinColumn(name = "PlaylistId")
    private Playlist playlistId;

 //CRIAR TABELA NO BANCO PARA RELACIONAR
    @ManyToOne
    @JoinColumn(name = "TipoUsuario")
    private TipoUsuario tipoUsuarioId;

}
