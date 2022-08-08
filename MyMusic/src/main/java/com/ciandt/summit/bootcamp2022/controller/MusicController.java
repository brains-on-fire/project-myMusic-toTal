package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.MusicaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    MusicaService musicaService;

    @Autowired
    MusicaRepository musicaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class), @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)})
    public ResponseEntity<Object> getByNameOrArtist(@RequestParam(value = "filtro", required = false) String filtro) {

        if (filtro == null)
            return ResponseHandler.badRequest("The 'filter' parameter is required.");

        if (filtro.length() < 3)
            return ResponseHandler.badRequest("Invalid filter entered (less than 3 characters): " + filtro);

        Optional<MusicaDTO> musicas = musicaService.findByNameArtistOrMusic(filtro);

        if (musicas.isEmpty())
            return ResponseHandler.noContent("Music not found with filter: " + filtro);

        return ResponseHandler.ok(musicas.get(), musicas.stream().count() + "search result with filter: " + filtro);




    }


}

