package com.ciandt.summit.bootcamp2022.exceptions;

import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MusicaNaoEncontradaException.class)
    public ResponseEntity<Object> handleMusicaNaoEncontradaException(MusicaNaoEncontradaException err){
        return ResponseHandler.badRequest("Música não encontrada.");
    }

    @ExceptionHandler(PlaylistNaoEncontrada.class)
    public ResponseEntity<Object> handlePlaylistNaoEncontrada(PlaylistNaoEncontrada err){
        return ResponseHandler.badRequest("Playlist não encontrada.");
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<Object> handleMusicaJaCadastradaNaPlaylistException(ConstraintViolationException err){
        return ResponseHandler.badRequest("Música já cadastrada na Playlist.");
    }

    @ExceptionHandler(PayloadInvalidoException.class)
    public ResponseEntity<Object> handlePayloadInvalidoException(PayloadInvalidoException err){
        return ResponseHandler.badRequest("Payload Inválido.");
    }



}
