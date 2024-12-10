package com.example.assignment9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class BookException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>handleException(Exception ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status",HttpStatus.NOT_FOUND.value());
        response.put("error", "Not found exception");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    }
}
