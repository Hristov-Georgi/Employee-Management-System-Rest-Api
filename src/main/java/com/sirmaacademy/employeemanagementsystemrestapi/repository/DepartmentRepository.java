package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Optional<Department> findByNameIgnoreCase(String department);
    boolean existsByName(String name);
}
