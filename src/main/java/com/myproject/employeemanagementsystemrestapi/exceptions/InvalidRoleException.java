package com.sirmaacademy.employeemanagementsystemrestapi.exceptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }
}
