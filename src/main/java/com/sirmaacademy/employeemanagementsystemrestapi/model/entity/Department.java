package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name, Set<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
