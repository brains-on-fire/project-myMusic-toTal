package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.TipoUsuario;
import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    UsuarioService usuarioService;

    @DisplayName("Buscar usuário por id que existe")
    @Test
    public void DeveRetornarUsuarioPorId(){

        TipoUsuario tipoUsuarioPremium = TipoUsuario.builder().id("2").descricao("premium").build();

        Usuario usuario = Usuario.builder().id("2").tipoUsuarioId(tipoUsuarioPremium).nome("User Test").build();

        when(usuarioService.findUsersById("2")).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.findUsersById("2");

        Assertions.assertTrue(result.isPresent());

    }

    @DisplayName("Buscar usuário por id que não existe")
    @Test
    public void DeveRetornarUsuarioNaoEncontrado(){

        TipoUsuario tipoUsuarioPremium = TipoUsuario.builder().id("2").descricao("premium").build();

        Usuario usuario = Usuario.builder().id("2").tipoUsuarioId(tipoUsuarioPremium).nome("User Test").build();

        when(usuarioService.findUsersById("2")).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.findUsersById("3");

        Assertions.assertTrue(result.isEmpty());

    }
}
