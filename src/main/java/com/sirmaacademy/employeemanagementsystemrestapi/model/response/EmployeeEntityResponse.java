package com.sirmaacademy.employeemanagementsystemrestapi.model.response;

public class EmployeeEntityResponse {

    private String firstName;
    private String lastName;
    private String department;
    private String position;

    public EmployeeEntityResponse(String firstName, String lastName, String department, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
