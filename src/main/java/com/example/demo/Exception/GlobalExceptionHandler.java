package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleNullPointerException(UrlNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Global Error: " + ex.getMessage());
    }
}
