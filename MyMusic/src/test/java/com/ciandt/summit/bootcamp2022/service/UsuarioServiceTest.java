package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.entity.User;
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

        UserType tipoUsuarioPremium = UserType.builder().id("2").descricao("premium").build();

        User usuario = User.builder().id("2").userTypeId(tipoUsuarioPremium).nome("User Test").build();

        when(usuarioService.findUsersById("2")).thenReturn(Optional.of(usuario));

        Optional<User> result = usuarioService.findUsersById("2");

        Assertions.assertTrue(result.isPresent());

    }

    @DisplayName("Buscar usuário por id que não existe")
    @Test
    public void DeveRetornarUsuarioNaoEncontrado(){

        UserType tipoUsuarioPremium = UserType.builder().id("2").descricao("premium").build();

        User usuario = User.builder().id("2").userTypeId(tipoUsuarioPremium).nome("User Test").build();

        when(usuarioService.findUsersById("2")).thenReturn(Optional.of(usuario));

        Optional<User> result = usuarioService.findUsersById("3");

        Assertions.assertTrue(result.isEmpty());

    }
}
