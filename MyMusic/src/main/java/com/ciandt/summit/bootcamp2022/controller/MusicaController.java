package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.service.MusicaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "token", value = "token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<Object> getByNameOrArtist(@RequestParam String filtro) {
        return musicaService.findByNameArtistOrMusic(filtro);
    }
}

