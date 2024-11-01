package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    boolean existsByPersonalIdNumber(Integer personalIdNumber);

    Optional<List<Employee>> findAllByFirstName(String firstName);

    @Query("SELECT e FROM Employee e WHERE e.status = 'ACTIVE'") //TODO: check query
    Optional<List<Employee>> findAllWithStatusActive();
}
