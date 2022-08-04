package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.TipoUsuario;
import com.ciandt.summit.bootcamp2022.entity.Usuario;
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

        Musica novaMusica = musicaRepository.findById(musicaDTO.getData().get(0).getId()).orElseThrow(() -> new MusicaNaoEncontradaException());
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNaoEncontrada());
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontrado());

        Integer countMusicas = playlist.countMusica(playlist);

        if ((playlist != null) && (novaMusica != null) && (usuario != null)){

            if (playlist.getMusicas() != null && playlist.getMusicas().contains(novaMusica))
                throw new MusicaJaCadastradaNaPlaylistException();

            if (usuario.getTipoUsuarioId().getDescricao().equals("comum")  && countMusicas > 5)
                throw new QuantMusicaExcedidaException();


            playlist.addMusica(novaMusica);
            playlistRepository.save(playlist);


            List<Musica> musicaAdicionada = new ArrayList<>();
            musicaAdicionada.add(novaMusica);

            MusicaDTO musicaDTOAdicionada = new MusicaDTO(musicaAdicionada);

            return Optional.of(musicaDTOAdicionada);
        }

        return Optional.empty();
    }

    public void removeMusicaFromPlaylist(String playlistid, String musicaId){
        Playlist playlist = playlistRepository.findById(playlistid).orElseThrow(() -> new PlaylistNaoEncontrada());
        Musica musica = musicaRepository.findById(musicaId).orElseThrow(() -> new MusicaNaoEncontradaException());

        if (!playlist.getMusicas().contains(musica))
            throw new MusicaNaoCadastradaNaPlaylistException();

        playlist.removeMusica(musica);
        playlistRepository.save(playlist);
    }

    public Optional<List<Musica>> findPlaylistMusicasByPlaylistId(String id) {

        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new PlaylistNaoEncontrada();

        return Optional.of(playlist.get().getMusicas());
    }

    public Optional<List<Playlist>> findAll() {
        return Optional.of(playlistRepository.findAll());
    }

    private boolean isPayloadValid(MusicaDTO musicaDTO){
        if (musicaDTO == null || musicaDTO.getData() == null || musicaDTO.getData().size() == 0)
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