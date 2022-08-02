package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.TipoUsuario;
import com.ciandt.summit.bootcamp2022.repository.TipoUsuarioRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usertype")
public class TipoUsuarioController {
/*
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return ResponseHandler.ok(tipoUsuarioRepository.findAll(), "Busca todos os tipos de usuários");

    }

    @PostMapping("/add")
    public ResponseEntity<TipoUsuario> addTipoDeUsuario(@RequestBody TipoUsuario tipoUsuario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipoUsuario));

    }*/

}


