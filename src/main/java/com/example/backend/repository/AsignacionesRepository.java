package com.example.backend.repository;

import com.example.backend.model.Asignaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionesRepository extends JpaRepository<Asignaciones, Long> {
}