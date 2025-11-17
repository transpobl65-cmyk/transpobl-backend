package com.example.backend.repository;

import com.example.backend.model.Coordinaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinacionesRepository extends JpaRepository<Coordinaciones, Long> {
}