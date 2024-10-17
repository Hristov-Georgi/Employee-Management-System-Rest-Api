package com.sirmaacademy.employeemanagementsystemrestapi.service;

import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.LoginRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.LoginResponse;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.RegisteredEmployeeResponse;

public interface AuthService {

    LoginResponse confirmLogin(LoginRequest loginRequest);
    RegisteredEmployeeResponse confirmRegistration(EmployeeRegisterRequest employeeRegisterRequest);
}
