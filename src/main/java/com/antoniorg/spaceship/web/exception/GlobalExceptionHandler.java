package com.antoniorg.spaceship.web.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.antoniorg.spaceship.application.exception.ItemNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex, WebRequest request) {
        var body = new HashMap<String, String>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", "An error occurred");
        body.put("details", ex.getMessage());
        
        log.error(body.toString(), ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        var body = new HashMap<String, String>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", ex.getMessage());
        
        log.error(body.toString(), ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleIItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        var body = new HashMap<String, String>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}