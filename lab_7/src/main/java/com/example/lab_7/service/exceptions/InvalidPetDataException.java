package com.example.lab_7.service.exceptions;

public class InvalidPetDataException extends RuntimeException {
    public InvalidPetDataException(String message) {
        super(message);
    }

}
