package com.example.backend.services;
import com.example.backend.model.Vehiculos;
import com.example.backend.repository.VehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculosService {

    @Autowired
    private VehiculosRepository repository;

    public void insert(Vehiculos v) {
        repository.save(v);
    }

    public List<Vehiculos> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Vehiculos listarId(Long id) {
        return repository.findById(id).orElse(new Vehiculos());
    }
}