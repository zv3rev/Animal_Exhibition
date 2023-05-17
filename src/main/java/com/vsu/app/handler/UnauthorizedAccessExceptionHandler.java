package com.vsu.app.handler;

import com.vsu.app.exception.UnauthorizedAccessException;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@ControllerAdvice
@Log
public class UnauthorizedAccessExceptionHandler {
    @ExceptionHandler(UnauthorizedAccessException.class)
    protected ResponseEntity<Object> handleBindException(UnauthorizedAccessException e)
    {
        log.log(Level.INFO, "Raised unauthorized access exception with message: " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
