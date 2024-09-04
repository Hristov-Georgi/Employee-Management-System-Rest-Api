package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.Random;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Account() {
    }

    public Account(String firstName, String lastName, String password) {
        this.username = buildUsername(firstName, lastName);
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String buildUsername(String firstName, String lastName) {
        Random rnd = new Random();
        int lowerBound = 1000;
        int upperBound = 10000;
        int number = rnd.nextInt(lowerBound, upperBound);

        return String.join("",firstName, lastName, String.valueOf(number));
    }

}
