package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

}
