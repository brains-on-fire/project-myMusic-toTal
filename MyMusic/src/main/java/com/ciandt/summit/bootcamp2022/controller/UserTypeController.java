package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.repository.TipoUsuarioRepository;
import com.ciandt.summit.bootcamp2022.service.TipoUsuarioService;
import com.ciandt.summit.bootcamp2022.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usertype")
public class UserTypeController {

    @Autowired
    TipoUsuarioService tipoUsuarioService;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());

    }
}


