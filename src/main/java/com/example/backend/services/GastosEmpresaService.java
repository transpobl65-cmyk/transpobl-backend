package com.example.backend.services;
import com.example.backend.model.GastosEmpresa;
import com.example.backend.repository.GastosEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosEmpresaService {

    @Autowired
    private GastosEmpresaRepository gastosEmpresaRepository;

    public void insert(GastosEmpresa g) {
        gastosEmpresaRepository.save(g);
    }

    public List<GastosEmpresa> list() {
        return gastosEmpresaRepository.findAll();
    }

    public void delete(Long id) {
        gastosEmpresaRepository.deleteById(id);
    }

    public GastosEmpresa listarId(Long id) {
        return gastosEmpresaRepository.findById(id).orElse(new GastosEmpresa());
    }
}