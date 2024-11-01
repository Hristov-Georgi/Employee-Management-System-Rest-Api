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

}
