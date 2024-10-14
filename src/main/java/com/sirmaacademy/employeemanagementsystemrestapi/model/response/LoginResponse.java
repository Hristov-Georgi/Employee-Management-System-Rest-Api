package com.sirmaacademy.employeemanagementsystemrestapi.model.response;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Role;

import java.util.Date;
import java.util.List;

public class LoginResponse {

    private String jwtToken;
    private Date expiresIn;
    private String username;
    private List<String> roles;

    public LoginResponse() {
    }

    public LoginResponse setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public LoginResponse setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public LoginResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginResponse setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
