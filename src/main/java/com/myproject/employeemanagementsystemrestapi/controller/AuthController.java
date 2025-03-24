package com.sirmaacademy.employeemanagementsystemrestapi.controller;

import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.EmployeeAlreadyExistsException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidDepartmentException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidPositionException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidRoleException;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.LoginRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.LoginResponse;
import com.sirmaacademy.employeemanagementsystemrestapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            LoginResponse response = authService.confirmLogin(loginRequest);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password.", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected exception occurred.");
        }
    }

}
