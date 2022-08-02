package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Table(name = "TipoUsuario")
public class TipoUsuario {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id = UUID.randomUUID().toString();

    @Column(name = "Descricao")
    private String descricao;

}
