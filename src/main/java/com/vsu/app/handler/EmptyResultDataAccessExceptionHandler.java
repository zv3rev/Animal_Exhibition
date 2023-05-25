package com.vsu.app.handler;

import lombok.extern.java.Log;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@ControllerAdvice
@Log
public class EmptyResultDataAccessExceptionHandler {
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity handleEmptyResultDataAccessException(EmptyResultDataAccessException e){
        log.log(Level.INFO, "Access to a non-existent resource");
        return ResponseEntity.notFound().build();
    }

}
