package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    MusicaRepository musicaRepository;
    @Autowired
    PlaylistRepository playlistRepository;


        public Musica addMusicaToPlaylist(String playlistId, Musica musica){
            Musica novaMusica = musicaRepository.findById(musica.getId()).orElseThrow(() -> new RuntimeException("Musica não encontrada!"));
            Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist não encontrada!"));

            List<Musica> musicaList = new ArrayList<>();
            musicaList.add(novaMusica);

            if(playlist.getId() == playlistId){
                if(novaMusica.getId() == musica.getId()){
                    playlist.setMusicas(musicaList);
                    playlistRepository.save(playlist);
                }
            }
            return musica;
        }
    }