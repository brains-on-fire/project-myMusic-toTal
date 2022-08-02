package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.exceptions.PlaylistNaoEncontrada;
import com.ciandt.summit.bootcamp2022.exceptions.UsuarioNaoEncontrado;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<List<Usuario>> findAll() {
        Optional<List<Usuario>> usuario = Optional.of(usuarioRepository.findAll());
        return usuario;
    }


    public Optional<Usuario> findUsersById(String usuarioId) {

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if (usuario.isEmpty())
            throw new UsuarioNaoEncontrado();

        return usuario;
    }

}
