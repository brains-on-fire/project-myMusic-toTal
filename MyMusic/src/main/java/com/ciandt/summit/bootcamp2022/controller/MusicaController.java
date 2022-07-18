package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.MusicaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    MusicaService musicaService;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<Object> getByNameOrArtist(@RequestParam String filtro) {

        if (filtro.length() < 3)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<List<Musica>> musicas = musicaService.findByNameArtistOrMusic(filtro);

        if (musicas.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
        return ResponseHandler.generateResponse(musicas.get(), HttpStatus.OK);
    }
}

