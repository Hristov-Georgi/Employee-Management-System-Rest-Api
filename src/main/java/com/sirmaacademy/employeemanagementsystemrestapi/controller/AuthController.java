package com.sirmaacademy.employeemanagementsystemrestapi.controller;

import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.EmployeeAlreadyExistsException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidDepartmentException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidPositionException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidRoleException;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.LoginRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.LoginResponse;
import com.sirmaacademy.employeemanagementsystemrestapi.service.AuthService;
import com.sirmaacademy.employeemanagementsystemrestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @Autowired
    public AuthController(EmployeeService employeeService, AuthenticationManager authenticationManager,
                          AuthService authService) {
        this.employeeService = employeeService;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody EmployeeRegisterRequest employeeRegisterRequest) {

        try {
            String response = authService.confirmRegistration(employeeRegisterRequest);
            return ResponseEntity.ok(response);
        } catch (EmployeeAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee is already in the system.", e);
        } catch (InvalidDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department.", e);
        } catch (InvalidPositionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Position.", e);
        } catch (InvalidRoleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role.", e);
        }

    }




}
