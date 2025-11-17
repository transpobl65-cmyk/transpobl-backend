package com.example.backend.services;
import com.example.backend.model.Coordinaciones;
import com.example.backend.repository.CoordinacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinacionesService {

    @Autowired
    private CoordinacionesRepository repository;

    public void insert(Coordinaciones c) {
        repository.save(c);
    }

    public List<Coordinaciones> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Coordinaciones listarId(Long id) {
        return repository.findById(id).orElse(new Coordinaciones());
    }
}