package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;

import com.sirmaacademy.employeemanagementsystemrestapi.model.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String middleName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 10)
    private Integer personalIdNumber;

    @Column(nullable = false)
    private BigDecimal salary;

    @ManyToOne()
    private Department department;

    @ManyToOne
    private Position position;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToMany
    private Set<Role> roles;

    public Employee() {
        this.roles = new HashSet<>();
    }

    public Employee(String firstName, String middleName, String lastName, Integer personalIdNumber,
                    Department department, Position position,
                    BigDecimal salary, Set<Role> roles) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.status = Status.ACTIVE;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(Integer personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }
}
