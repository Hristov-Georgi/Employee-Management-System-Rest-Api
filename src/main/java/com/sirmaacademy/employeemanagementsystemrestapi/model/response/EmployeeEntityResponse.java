package com.sirmaacademy.employeemanagementsystemrestapi.model.response;

public class EmployeeEntityResponse {

    private String firstName;
    private String lastName;
    private String departmentName;
    private String position;

    public EmployeeEntityResponse(String firstName, String lastName, String departmentName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
