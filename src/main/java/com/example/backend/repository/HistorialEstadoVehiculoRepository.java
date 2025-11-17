package com.example.backend.repository;
import com.example.backend.model.HistorialEstadoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialEstadoVehiculoRepository extends JpaRepository<HistorialEstadoVehiculo, Long> {
}