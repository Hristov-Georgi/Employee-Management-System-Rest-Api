package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
