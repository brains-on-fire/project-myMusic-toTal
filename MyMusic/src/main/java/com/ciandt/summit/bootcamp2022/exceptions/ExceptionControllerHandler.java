package com.ciandt.summit.bootcamp2022.exceptions;

import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MusicaNaoEncontradaException.class)
    public ResponseEntity<Object> handleMusicaNaoEncontradaException(MusicaNaoEncontradaException err) {
        return ResponseHandler.badRequest("Música não encontrada.");
    }

    @ExceptionHandler(PlaylistNaoEncontrada.class)
    public ResponseEntity<Object> handlePlaylistNaoEncontrada(PlaylistNaoEncontrada err) {
        return ResponseHandler.badRequest("Playlist não encontrada.");
    }

    @ExceptionHandler(MusicaJaCadastradaNaPlaylistException.class)
    public ResponseEntity<Object> handleMusicaJaCadastradaNaPlaylistException(MusicaJaCadastradaNaPlaylistException err) {
        return ResponseHandler.badRequest("Música já cadastrada na Playlist.");
    }

    @ExceptionHandler(PayloadInvalidoException.class)
    public ResponseEntity<Object> handlePayloadInvalidoException(PayloadInvalidoException err) {
        return ResponseHandler.badRequest("Payload Inválido.");
    }

    @ExceptionHandler(MusicaNaoCadastradaNaPlaylistException.class)
    public ResponseEntity<Object> handleMusicaNaoCadastradaNaPlaylistException(MusicaNaoCadastradaNaPlaylistException err){
        return ResponseHandler.badRequest("Música não cadastrada na Playlist");
    }

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontrado err){
        return ResponseHandler.badRequest("Usuario não encontrado");
    }

}
