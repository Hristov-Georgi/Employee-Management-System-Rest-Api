package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    boolean existsByPersonalIdNumber(Integer personalIdNumber);
}
