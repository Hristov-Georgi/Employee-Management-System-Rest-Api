package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;

import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.EmployeeAlreadyExistsException;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.*;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.LoginRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.LoginResponse;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.*;
import com.sirmaacademy.employeemanagementsystemrestapi.service.AuthService;
import com.sirmaacademy.employeemanagementsystemrestapi.validation.Validate;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceLogic implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AuthServiceLogic(AuthenticationManager authenticationManager, JwtService jwtService,
                            AccountRepository accountRepository, EmployeeRepository employeeRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LoginResponse confirmLogin(LoginRequest loginRequest) {

        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authRequest);

        String jwtToken = jwtService.createToken(authentication.getName());

        Account account = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Username "
                                + authentication.getName()
                                + " not found."));

        List<String> roleList = account.getEmployee()
                .getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        return new LoginResponse()
                .setJwtToken(jwtToken)
                .setUsername(account.getUsername())
                .setRoles(roleList)
                .setExpiresIn(jwtService.extractClaim(jwtToken, Claims::getExpiration));
    }

    @Override
    public String confirmRegistration(EmployeeRegisterRequest employeeRegisterRequest) {

        if (this.employeeRepository.existsByPersonalIdNumber(employeeRegisterRequest.getPersonalIdNumber())) {
            throw new EmployeeAlreadyExistsException(
                    "Employee with id: '"
                            + employeeRegisterRequest.getPersonalIdNumber()
                            + "' is already registered.");
        }

        Employee employee = employeeRepository.save(
                new Employee(
                employeeRegisterRequest.getFirstName(),
                employeeRegisterRequest.getMiddleName(),
                employeeRegisterRequest.getLastName(),
                employeeRegisterRequest.getPersonalIdNumber(),
                Validate.department(employeeRegisterRequest.getDepartment()),
                Validate.position(employeeRegisterRequest.getPosition()),
                employeeRegisterRequest.getSalary(),
                Validate.roles(employeeRegisterRequest.getRoles())));

        Account account = accountRepository.save(createEmployeeAccount(employee));

        Print.printAccountDetails(account);

        return String.format("Employee: %s, %s, %s was successfully registered." +
                " %s %s account was created successfully.",
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName());
    }

    private Account createEmployeeAccount(Employee employee) {
        return new Account(employee.getFirstName(), employee.getLastName(), employee);
    }

}
