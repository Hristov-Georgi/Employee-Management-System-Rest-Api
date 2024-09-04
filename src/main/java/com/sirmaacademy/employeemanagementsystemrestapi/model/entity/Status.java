package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

/**
 * This class is used to show employees status in the company.
 * If it is current employee status = ACTIVE,
 * else if it is not working anymore for the company status = FIRED;
 */

@Entity
@Table(name = "status")
public class Status {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
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
}
