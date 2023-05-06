package com.vsu.app.exception;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@ControllerAdvice
@Log
public class GlobalValidationExceptionHandler {
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBindException(BindException e){
        List<String> errors = e.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.log(Level.INFO,
                String.format("Bind exception in: %s, error count: %d.",
                    e.getBindingResult().getTarget().toString(),
                    e.getAllErrors().size()));
        return ResponseEntity.badRequest().body(errors);
    }
}
