package com.sirmaacademy.employeemanagementsystemrestapi.validation;


import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidDepartmentException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidPositionException;
import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.InvalidRoleException;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Department;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Position;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Role;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.DepartmentRepository;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.PositionRepository;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public final class Validate {

    @Autowired
    private static RoleRepository roleRepository;

    @Autowired
    private static PositionRepository positionRepository;

    @Autowired
    private static DepartmentRepository departmentRepository;

    private Validate() {
    }

    public static Set<Role> roles(Set<String> roles) {
        Set<Role> roleSet = new HashSet<>();

        for (String r : roles) {
            roleSet.add(roleRepository.findByNameIgnoreCase(r)
                    .orElseThrow(() -> new InvalidRoleException("Role: '" + r + "' does not exist.")));
        }
        return roleSet;
    }

    public static Position position(String positionName) {
        return positionRepository.findByNameIgnoreCase(positionName)
                .orElseThrow(() -> new InvalidPositionException(
                        "Position: '"
                                + positionName
                                + "' does not exist."));
    }

    public static Department department(String departmentName) {
        return departmentRepository.findByNameIgnoreCase(departmentName)
                .orElseThrow(() -> new InvalidDepartmentException(
                        "Department: '"
                                + departmentName
                                + "' does not exist."));
    }

}
