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
        return ResponseHandler.badRequest("Music not found.");
    }

    @ExceptionHandler(PlaylistNaoEncontrada.class)
    public ResponseEntity<Object> handlePlaylistNaoEncontrada(PlaylistNaoEncontrada err) {
        return ResponseHandler.badRequest("Playlist not found.");
    }

    @ExceptionHandler(MusicaJaCadastradaNaPlaylistException.class)
    public ResponseEntity<Object> handleMusicaJaCadastradaNaPlaylistException(MusicaJaCadastradaNaPlaylistException err) {
        return ResponseHandler.badRequest("Music already registered in the Playlist.");
    }

    @ExceptionHandler(PayloadInvalidoException.class)
    public ResponseEntity<Object> handlePayloadInvalidoException(PayloadInvalidoException err) {
        return ResponseHandler.badRequest("Payload invalid.");
    }

    @ExceptionHandler(MusicaNaoCadastradaNaPlaylistException.class)
    public ResponseEntity<Object> handleMusicaNaoCadastradaNaPlaylistException(MusicaNaoCadastradaNaPlaylistException err){
        return ResponseHandler.badRequest("Music not registered in Playlist.");
    }

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontrado err){
        return ResponseHandler.badRequest("User not found.");
    }

    @ExceptionHandler(QuantMusicaExcedidaException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(QuantMusicaExcedidaException err){
        return ResponseHandler.badRequest("You have reached the maximum number of songs in your playlist. To add more songs, purchase the Premium plan.");
    }

}
