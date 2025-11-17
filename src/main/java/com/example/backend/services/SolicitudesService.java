package com.example.backend.services;
import com.example.backend.model.Solicitudes;
import com.example.backend.repository.SolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudesService {

    @Autowired
    private SolicitudesRepository repository;

    public void insert(Solicitudes s) {
        repository.save(s);
    }

    public List<Solicitudes> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Solicitudes listarId(Long id) {
        return repository.findById(id).orElse(new Solicitudes());
    }
}