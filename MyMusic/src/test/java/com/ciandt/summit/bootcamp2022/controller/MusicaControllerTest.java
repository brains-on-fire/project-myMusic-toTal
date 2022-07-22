package com.ciandt.summit.bootcamp2022.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class MusicaControllerTest {

    @Autowired
    MusicaController musicaController;

    @Test
    @Order(1)
    @DisplayName("Busca musica > 3 caracteres")
    public void deveRetornarMusicas(){
        ResponseEntity<Object> result = musicaController.getByNameOrArtist("The");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @Order(1)
    @DisplayName("Busca musica com parametro vazio")
    public void deveRetornarBadRequest(){
        ResponseEntity<Object> result = musicaController.getByNameOrArtist("");
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Busca musica < 3 caracteres")
    public void deveRetornarErroNaRequisicao(){
        ResponseEntity<Object> result = musicaController.getByNameOrArtist("th");
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Busca que retorna vazio")
    public void deveRetornarVazio(){
        ResponseEntity<Object> result = musicaController.getByNameOrArtist("tasdad");
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    @Order(4)
    @DisplayName("Busca INSENSITIVE LETTER")
    public void deveRetornarResultadoNormal(){
        ResponseEntity<Object> result = musicaController.getByNameOrArtist("THE");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
