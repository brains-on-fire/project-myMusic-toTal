package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.config.LogConfig;
import com.ciandt.summit.bootcamp2022.entity.Musica;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    @Autowired
    MusicaService musicaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class), @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)})
    public ResponseEntity<Object> getByNameOrArtist(@RequestParam(value = "filtro", required = false) String filtro) {

        if (filtro == null)
            return ResponseHandler.badRequest("O parâmetro 'filtro' é obrigatório.");

        if (filtro.length() < 3)
            return ResponseHandler.badRequest("Filtro informado inválido (menor que 3 caracteres): " + filtro);

        Optional<List<Musica>> musicas = musicaService.findByNameArtistOrMusic(filtro);

        if (musicas.isEmpty())
            return ResponseHandler.noContent("Música não encontrada com o filtro: " + filtro);

        return ResponseHandler.ok(musicas.get(), musicas.get().size() + " músicas encontradas com o filtro: " + filtro);

    }
}

