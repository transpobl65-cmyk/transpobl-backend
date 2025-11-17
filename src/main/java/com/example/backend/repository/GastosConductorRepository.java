package com.example.backend.repository;

import com.example.backend.model.GastosConductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosConductorRepository extends JpaRepository<GastosConductor, Long> {
}