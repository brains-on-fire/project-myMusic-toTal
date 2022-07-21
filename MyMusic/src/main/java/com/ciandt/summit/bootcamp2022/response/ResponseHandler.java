package com.ciandt.summit.bootcamp2022.response;

import com.ciandt.summit.bootcamp2022.config.LogConfig;
import com.ciandt.summit.bootcamp2022.config.LogType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseHandler {

    private static final LogConfig log = new LogConfig(ResponseHandler.class);

    public static ResponseEntity<Object> ok(Object response, String message) {
        Set<Object> setResponse = new HashSet<>();
        setResponse.add(response);

        log.create(LogType.INFO, message);

        return new ResponseEntity<>(setResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> badRequest(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        log.create(LogType.ERROR, message);

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> noContent(String... logMessage) {
        if (logMessage != null)
            log.create(LogType.WARN, Arrays.toString(logMessage));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Object> created(Object response, String message) {
        Set<Object> setResponse = new HashSet<>();
        setResponse.add(response);

        log.create(LogType.INFO, message);

        return new ResponseEntity<>(setResponse, HttpStatus.CREATED);
    }
}