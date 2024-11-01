package com.sirmaacademy.employeemanagementsystemrestapi.controller;


import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.EmployeeAlreadyExistsException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidDepartmentException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidPositionException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidRoleException;
import com.sirmaacademy.employeemanagementsystemrestapi.mapper.EmployeeMapper;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.EmployeeEntityResponse;
import com.sirmaacademy.employeemanagementsystemrestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody EmployeeRegisterRequest employeeRegisterRequest) {

        try {
            String response = employeeService.confirmRegistration(employeeRegisterRequest);
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

    @GetMapping("/findBy/firstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam String firstName) {

        try {
            return ResponseEntity.ok(this.employeeService.findAllByFirstName(firstName));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    
    @GetMapping("/getAllActive")
    public ResponseEntity<List<EmployeeEntityResponse>> getActiveEmployees() {
        try {
            List<Employee> employees = this.employeeService.getAllActive();
            List<EmployeeEntityResponse> response = this.employeeMapper.employeeToEmployeeResponseList(employees);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
