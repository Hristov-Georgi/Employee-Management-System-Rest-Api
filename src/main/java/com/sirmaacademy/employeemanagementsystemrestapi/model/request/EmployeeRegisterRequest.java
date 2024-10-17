package com.sirmaacademy.employeemanagementsystemrestapi.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Set;

public class EmployeeRegisterRequest {
    private static final String MINIMUM_SALARY = "933";

    @NotBlank
    @Length(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String middleName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Range(min = 10, max = 10)
    private Integer personalIdNumber;

    @NotBlank
    @DecimalMin(value = MINIMUM_SALARY)
    private BigDecimal salary;

    @NotBlank
    private String department;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    @NotBlank
    @Size(min = 1)
    private Set<String> roles;

    public EmployeeRegisterRequest(String firstName, String middleName, String lastName,
                                   Integer personalIdNumber, BigDecimal salary, String department,
                                   String position, String status, Set<String> roles) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.status = status;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(Integer personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }
}
