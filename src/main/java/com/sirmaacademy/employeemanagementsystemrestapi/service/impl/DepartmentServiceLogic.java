package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Department;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.DepartmentRepository;
import com.sirmaacademy.employeemanagementsystemrestapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceLogic implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceLogic(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String addDepartment(String departmentName) {

        Department department = new Department(departmentName);
        this.departmentRepository.save(department);

        return String.format("Department %s was created successfully.", departmentName);
    }
}
