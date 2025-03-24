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

    public static Department ifDepartmentExist(String departmentName) {
        return departmentRepository.findByNameIgnoreCase(departmentName)
                .orElseThrow(() -> new InvalidDepartmentException(
                        "Department: '"
                                + departmentName
                                + "' does not exist."));
    }

    public static String departmentName(String departmentName) {

        if (departmentName.length() > 50) {
            throw new InvalidDepartmentException("Department name length is too long (more than 50 symbols).");
        }

        if (departmentRepository.existsByName(departmentName)) {
            throw new InvalidDepartmentException("Department '" + departmentName + "' already exists.");
        }

        for (char s : departmentName.toCharArray()) {

            if (s != 32 && s <= 64
                || s >= 91 && s <= 96
                || s >= 123) {
                throw new IllegalArgumentException("Invalid symbol '"
                        + s
                        + "' in department name: '"
                        + departmentName
                        + "'.");
            }

        }
        return departmentName;
    }


}
