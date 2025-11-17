package com.example.backend.services;

import com.example.backend.model.Clientes;
import com.example.backend.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository repository;

    public void insert(Clientes c) {
        repository.save(c);
    }

    public List<Clientes> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Clientes listarId(Long id) {
        return repository.findById(id).orElse(new Clientes());
    }
}