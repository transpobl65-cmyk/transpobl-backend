package com.example.backend.repository;

import com.example.backend.model.GastosEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosEmpresaRepository extends JpaRepository<GastosEmpresa, Long> {
}