package com.grupo11.readingsprocessor.database.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
