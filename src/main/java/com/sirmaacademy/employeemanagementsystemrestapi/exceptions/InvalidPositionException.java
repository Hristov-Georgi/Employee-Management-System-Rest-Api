package com.sirmaacademy.employeemanagementsystemrestapi.exceptions;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(String message) {
        super(message);
    }
}
