package com.example.backend.services;
import com.example.backend.model.GastosConductor;
import com.example.backend.repository.GastosConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosConductorService {

    @Autowired
    private GastosConductorRepository repository;

    public void insert(GastosConductor g) {
        repository.save(g);
    }

    public List<GastosConductor> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public GastosConductor listarId(Long id) {
        return repository.findById(id).orElse(new GastosConductor());
    }
}