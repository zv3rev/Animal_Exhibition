package com.vsu.app.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class SQLExceptionHandler {

    public static final String NOT_UNIQUE_CODE = "23505";
    public static final String WRONG_FOREIGN_KEY = "23503";

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<String> handleSQLException(SQLException e){
        String message = e.getMessage();
        String wrongValues;
        switch (e.getSQLState()){
            case WRONG_FOREIGN_KEY:
                wrongValues = message.substring(message.indexOf("("),message.lastIndexOf(")")+1);
                return ResponseEntity.badRequest().body("Wrong foreign key: " + wrongValues + " doesn't exist");
            case NOT_UNIQUE_CODE:
                wrongValues = message.substring(message.indexOf("=")+1, message.lastIndexOf("\""));
                return ResponseEntity.badRequest().body("Not unique value: " + wrongValues + " is already exists");
            default:
                return ResponseEntity.internalServerError().body("An error occurred in the database with code " + e.getSQLState());
        }
    }
}
