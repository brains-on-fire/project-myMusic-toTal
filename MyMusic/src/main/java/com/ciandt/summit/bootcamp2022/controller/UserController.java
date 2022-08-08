package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return ResponseHandler.ok(usuarioService.findAll(), "Search all users.");
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUsersById(@PathVariable(name = "userId") String userId) {
        return ResponseHandler.ok(usuarioService.findUsersById(userId), "Search user with ID");
    }

}
