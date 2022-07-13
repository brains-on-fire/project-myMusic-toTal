package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    MusicaService musicaService;

    @GetMapping
    public ResponseEntity<Object> getByNameOrArtist(@RequestParam String filtro) {
        return new ResponseEntity<>(musicaService.findByNameArtistOrMusic(filtro), HttpStatus.OK);
    }
}

