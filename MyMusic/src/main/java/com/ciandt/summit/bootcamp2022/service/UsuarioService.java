package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.dto.UsuarioDTO;
import com.ciandt.summit.bootcamp2022.entity.TipoUsuario;
import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.exceptions.UsuarioNaoEncontrado;
import com.ciandt.summit.bootcamp2022.repository.TipoUsuarioRepository;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

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

        public Optional<Usuario> migratePlanUser(String usuarioId, UsuarioDTO usuarioDTO) {

            Optional<TipoUsuario> tipoUsuarioPremium = tipoUsuarioRepository.findById("2");
            Optional<TipoUsuario> tipoUsuarioComum = tipoUsuarioRepository.findById("1");

            Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuarioId);

            String usuarioEdit = String.valueOf(usuarioRepository.findById(usuarioId));

/*            usuarioEncontrado.get().setTipoUsuarioId(tipoUsuarioComum);*/

/*            UsuarioDTO usuarioDtoModificado = new UsuarioDTO(musicaAdicionada);*/

            return usuarioEncontrado;

        }
}