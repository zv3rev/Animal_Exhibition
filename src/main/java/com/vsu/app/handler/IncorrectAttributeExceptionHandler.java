package com.vsu.app.handler;

import com.vsu.app.exception.IncorrectAttributeException;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@ControllerAdvice
@Log
public class IncorrectAttributeExceptionHandler {
    @ExceptionHandler(IncorrectAttributeException.class)
    protected ResponseEntity<String> handleIncorrectAttributeException(IncorrectAttributeException e){
        log.log(Level.INFO, "Raised incorrect attribute exception with message: " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
