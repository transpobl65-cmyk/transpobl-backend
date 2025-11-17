package com.example.backend.controllers;

import com.example.backend.dto.CuotasMensualesConductorDTO;
import com.example.backend.model.CuotasMensualesConductor;
import com.example.backend.services.CuotasMensualesConducService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuotas-mensuales")
public class CuotasMensualesConducController {

    @Autowired
    private CuotasMensualesConducService service;

    @PostMapping
    public void registrar(@RequestBody CuotasMensualesConductorDTO dto) {
        ModelMapper m = new ModelMapper();
        CuotasMensualesConductor c = m.map(dto, CuotasMensualesConductor.class);
        service.insert(c);
    }

    @PutMapping
    public void modificar(@RequestBody CuotasMensualesConductorDTO dto) {
        ModelMapper m = new ModelMapper();
        CuotasMensualesConductor c = m.map(dto, CuotasMensualesConductor.class);
        service.insert(c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public CuotasMensualesConductorDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), CuotasMensualesConductorDTO.class);
    }

    @GetMapping
    public List<CuotasMensualesConductorDTO> listar() {
        return service.list().stream().map(c -> {
            ModelMapper m = new ModelMapper();
            return m.map(c, CuotasMensualesConductorDTO.class);
        }).collect(Collectors.toList());
    }
}