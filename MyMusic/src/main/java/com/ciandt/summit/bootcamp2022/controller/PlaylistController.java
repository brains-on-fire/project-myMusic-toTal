package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping(value = "/findbyid", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true, paramType = "header", dataTypeClass = String.class), @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataTypeClass = String.class)})
    public ResponseEntity<Object> findMusicasByPlaylistId(@RequestParam(value = "id", required = false) String id) {
        return ResponseHandler.ok(playlistService.findPlaylistMusicasByPlaylistId(id), "Busca de Músicas por ID de Playlist!");
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return ResponseHandler.ok(playlistService.findAll(), "Busca de todas as Músicas de todas as Playlists!");
    }

    @PostMapping("/{userId}/{playlistId}/musicas")
    public ResponseEntity<Object> addMusicaToPlaylist(
            @PathVariable(name = "userId") String userId,
            @PathVariable(name = "playlistId") String playlistId,
            @Valid @RequestBody MusicaDTO musica) {
        Optional<MusicaDTO> response = playlistService.addMusicaToPlaylist(userId, playlistId, musica);
        return ResponseHandler.created(response, "Coleção de músicas cadastrada com sucesso");
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Object> removeMusicaFromPlaylist(@PathVariable(name = "playlistId") String playlistId, @PathVariable(name = "musicaId") String musicaId){
        playlistService.removeMusicaFromPlaylist(playlistId, musicaId);
        return ResponseHandler.deleted("Registro deletado com sucesso. PlaylistID: " + playlistId + ", MusicaID: " + musicaId);
    }
}
