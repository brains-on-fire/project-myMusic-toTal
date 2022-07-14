package com.ciandt.summit.bootcamp2022.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(Object response, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", response);
        return new ResponseEntity<>(map, status);
    }
}