package com.sirmaacademy.employeemanagementsystemrestapi.model.request;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginRequest {

    @NotBlank
    @Length(min = 4, max = 51)
    private String username;

    @NotBlank
    @Length(min = 7)
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
