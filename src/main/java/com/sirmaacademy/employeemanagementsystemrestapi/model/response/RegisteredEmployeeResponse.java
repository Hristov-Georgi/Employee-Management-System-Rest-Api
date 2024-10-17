package com.sirmaacademy.employeemanagementsystemrestapi.model.response;

public class RegisteredEmployeeResponse {

    private String id;
    private String firstName;
    private String lastName;

    public RegisteredEmployeeResponse(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
