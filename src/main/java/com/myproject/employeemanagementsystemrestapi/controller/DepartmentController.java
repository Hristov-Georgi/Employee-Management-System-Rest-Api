package com.sirmaacademy.employeemanagementsystemrestapi.controller;

import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidDepartmentException;
import com.sirmaacademy.employeemanagementsystemrestapi.service.DepartmentService;
import com.sirmaacademy.employeemanagementsystemrestapi.validation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/createNewDepartment")
    public String addDepartment(@RequestParam String departmentName) {

        try {
            String validatedName = Validate.departmentName(departmentName);
            return this.departmentService.addDepartment(validatedName);

        } catch (IllegalArgumentException | InvalidDepartmentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
