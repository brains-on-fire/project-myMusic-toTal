package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exceptions.MusicaNaoEncontradaException;
import com.ciandt.summit.bootcamp2022.exceptions.PayloadInvalidoException;
import com.ciandt.summit.bootcamp2022.exceptions.PlaylistNaoEncontrada;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    MusicaRepository musicaRepository;
    @Autowired
    PlaylistRepository playlistRepository;


    public Optional<MusicaDTO> addMusicaToPlaylist(String playlistId, MusicaDTO musicaDTO) {

        if (!isPayloadValid(musicaDTO))
            throw new PayloadInvalidoException();


        Musica novaMusica = musicaRepository.findById(musicaDTO.getData().get(0).getId()).orElseThrow(() -> new MusicaNaoEncontradaException());
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNaoEncontrada());

        if ((playlist != null) && (novaMusica != null)) {
            playlist.addMusica(novaMusica);
            playlistRepository.save(playlist);
            return Optional.of(musicaDTO);
        }

        return Optional.empty();
    }

    public Optional<List<Musica>> findPlaylistMusicasByPlaylistId(String id) {
        return Optional.of(playlistRepository.findById(id).get().getMusicas());
    }

    public Optional<List<Playlist>> findAll() {
        return Optional.of(playlistRepository.findAll());
    }

    private boolean isPayloadValid(MusicaDTO musicaDTO){
        if (musicaDTO.getData() == null)
            return false;

        Musica musicaCompare = musicaDTO.getData().get(0);

        if (musicaCompare.getId() == null ||
            musicaCompare.getNome() == null ||
            musicaCompare.getArtista() == null ||
            musicaCompare.getArtista().getId() == null ||
            musicaCompare.getArtista().getNome() == null)
            return false;

        return true;
    }

}