package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exceptions.*;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private MusicaRepository musicaRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Optional<MusicaDTO> addMusicaToPlaylist(String usuarioId, String playlistId, MusicaDTO musicaDTO){

        if (!isPayloadValid(musicaDTO))
            throw new PayloadInvalidoException();

        Music novaMusic = musicaRepository.findById(musicaDTO.getData().get(0).getId()).orElseThrow(() -> new MusicaNaoEncontradaException());
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNaoEncontrada());
        User user = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontrado());

        Integer countMusicas = playlist.countMusica(playlist);

        if ((playlist != null) && (novaMusic != null) && (user != null)){

            if (playlist.getMusic() != null && playlist.getMusic().contains(novaMusic))
                throw new MusicaJaCadastradaNaPlaylistException();

            if (user.getUserTypeId().getDescricao().equals("comum")  && countMusicas > 5)
                throw new QuantMusicaExcedidaException();


            playlist.addMusica(novaMusic);
            playlistRepository.save(playlist);


            List<Music> musicAdicionada = new ArrayList<>();
            musicAdicionada.add(novaMusic);

            MusicaDTO musicaDTOAdicionada = new MusicaDTO(musicAdicionada);

            return Optional.of(musicaDTOAdicionada);
        }

        return Optional.empty();
    }

    public void removeMusicaFromPlaylist(String playlistid, String musicaId){
        Playlist playlist = playlistRepository.findById(playlistid).orElseThrow(() -> new PlaylistNaoEncontrada());
        Music music = musicaRepository.findById(musicaId).orElseThrow(() -> new MusicaNaoEncontradaException());

        if (!playlist.getMusic().contains(music))
            throw new MusicaNaoCadastradaNaPlaylistException();

        playlist.removeMusica(music);
        playlistRepository.save(playlist);
    }

    public Optional<List<Music>> findPlaylistMusicasByPlaylistId(String id) {

        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new PlaylistNaoEncontrada();

        return Optional.of(playlist.get().getMusic());
    }

    public Optional<List<Playlist>> findAll() {
        return Optional.of(playlistRepository.findAll());
    }

    private boolean isPayloadValid(MusicaDTO musicaDTO){
        if (musicaDTO == null || musicaDTO.getData() == null || musicaDTO.getData().size() == 0)
            return false;

        Music musicCompare = musicaDTO.getData().get(0);

        if (musicCompare.getId() == null ||
            musicCompare.getNome() == null ||
            musicCompare.getArtist() == null ||
            musicCompare.getArtist().getId() == null ||
            musicCompare.getArtist().getNome() == null)
            return false;

        return true;
    }

}