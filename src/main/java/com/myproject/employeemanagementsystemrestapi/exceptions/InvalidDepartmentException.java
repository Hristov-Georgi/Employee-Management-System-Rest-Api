package com.sirmaacademy.employeemanagementsystemrestapi.exceptions;

public class InvalidDepartmentException extends RuntimeException {
    public InvalidDepartmentException(String message) {
        super(message);
    }
}
