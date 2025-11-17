package com.example.backend.services;
import com.example.backend.model.HistorialEstadoVehiculo;
import com.example.backend.repository.HistorialEstadoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialEstadoVehiculoService {

    @Autowired
    private HistorialEstadoVehiculoRepository repository;

    public void insert(HistorialEstadoVehiculo h) {
        repository.save(h);
    }

    public List<HistorialEstadoVehiculo> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public HistorialEstadoVehiculo listarId(Long id) {
        return repository.findById(id).orElse(new HistorialEstadoVehiculo());
    }
}