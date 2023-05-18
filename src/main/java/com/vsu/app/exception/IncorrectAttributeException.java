package com.vsu.app.exception;

public class IncorrectAttributeException extends Throwable {
    public IncorrectAttributeException(String incorrect_species_id) {
        super(incorrect_species_id);
    }
}
