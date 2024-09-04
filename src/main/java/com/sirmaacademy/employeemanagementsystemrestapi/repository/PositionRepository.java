package com.sirmaacademy.employeemanagementsystemrestapi.repository;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {

}
