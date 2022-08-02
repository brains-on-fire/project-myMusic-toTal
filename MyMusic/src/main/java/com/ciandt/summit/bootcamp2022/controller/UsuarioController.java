package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.UsuarioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return ResponseHandler.ok(usuarioService.findAll(), "Busca todos os Usuários");
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUsersById(@PathVariable(name = "userId") String userId) {
        return ResponseHandler.ok(usuarioService.findUsersById(userId), "Buscar Usuário por ID");
    }
}
