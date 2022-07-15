package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.MusicaEntity;
import com.ciandt.summit.bootcamp2022.service.IMusicaService;
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

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private IMusicaService iMusicaService;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)
    })

    public ResponseEntity<?> getByNameOrArtist(@RequestParam String filtro) {

        if (filtro.length() < 3)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<MusicaEntity> musics = iMusicaService.findByNameArtistOrMusic(filtro);

        if (musics.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(musics, HttpStatus.OK);
    }
}

