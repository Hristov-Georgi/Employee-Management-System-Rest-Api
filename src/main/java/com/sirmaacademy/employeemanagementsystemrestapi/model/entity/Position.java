package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    public Position() {
    }

    public Position(String name) {
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
