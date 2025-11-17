package com.example.backend.services;

import com.example.backend.model.Asignaciones;
import com.example.backend.repository.AsignacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionesService {

    @Autowired
    private AsignacionesRepository repository;

    public void insert(Asignaciones a) {
        repository.save(a);
    }

    public List<Asignaciones> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Asignaciones listarId(Long id) {
        return repository.findById(id).orElse(new Asignaciones());
    }
}