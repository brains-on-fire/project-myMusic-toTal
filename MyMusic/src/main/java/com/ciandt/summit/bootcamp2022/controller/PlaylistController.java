package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @GetMapping(value = "/findbyid", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class), @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)})
    @Transactional
    public Optional<List<Musica>> findByPlaylistId(@RequestParam(value = "id", required = false) String id) {

        List<Musica> queryResult = playlistRepository.findById(id).get().getMusicas();

        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(queryResult);
    }


    @GetMapping("/all")
    @Transactional
    public ResponseEntity<List<Playlist>> getAll() {
        return ResponseEntity.ok().body(playlistRepository.findAll());
    }

    @PostMapping("/{playlistId}/musicas")
    @Transactional
    public ResponseEntity<Musica> findByPlaylistId(@PathVariable(name = "playlistId") String playlistId, @RequestBody Musica musica) {
        return ResponseEntity.ok().body(playlistService.addMusicaToPlaylist(playlistId, musica));
    }
}
