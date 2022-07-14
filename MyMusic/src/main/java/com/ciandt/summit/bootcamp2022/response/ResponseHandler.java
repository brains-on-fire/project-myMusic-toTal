package com.ciandt.summit.bootcamp2022.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object response) {
        Map<String, Object> map = new HashMap<>();

        map.put("Mensagem", message);
        map.put("Status", status.value());
        map.put("Dados", response);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();

        map.put("Mensagem", message);
        map.put("Status", status.value());

        return new ResponseEntity<>(map, status);
    }
}