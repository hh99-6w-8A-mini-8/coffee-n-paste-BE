package com.mini.coffeenpastebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalException(IllegalArgumentException ex){
        return ResponseEntity
                .badRequest()
                .body(Map.entry("msg", ex.getMessage()));
    }

    @ExceptionHandler({CoffeeNPasteNotFoundException.class})
    public ResponseEntity<?> handleRuntimeException(CoffeeNPasteNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.entry("msg", ex.getMessage()));
    }
}
