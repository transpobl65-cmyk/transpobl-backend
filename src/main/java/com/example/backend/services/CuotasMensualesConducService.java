package com.example.backend.services;
import com.example.backend.model.CuotasMensualesConductor;
import com.example.backend.repository.CuotasMensualesConducRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotasMensualesConducService {

    @Autowired
    private CuotasMensualesConducRepository repository;

    public void insert(CuotasMensualesConductor cuota) {
        repository.save(cuota);
    }

    public List<CuotasMensualesConductor> list() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CuotasMensualesConductor listarId(Long id) {
        return repository.findById(id).orElse(new CuotasMensualesConductor());
    }
}
