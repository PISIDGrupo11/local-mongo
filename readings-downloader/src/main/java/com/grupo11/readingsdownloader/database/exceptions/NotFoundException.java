package com.grupo11.readingsdownloader.database.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
