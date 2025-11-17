package com.example.backend.controllers;

import com.example.backend.dto.VehiculosDTO;
import com.example.backend.model.Vehiculos;
import com.example.backend.services.VehiculosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehiculos")
public class VehiculosController {

    @Autowired
    private VehiculosService service;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody VehiculosDTO dto) {
        ModelMapper m = new ModelMapper();
        Vehiculos v = m.map(dto, Vehiculos.class);
        service.insert(v);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody VehiculosDTO dto) {
        ModelMapper m = new ModelMapper();
        Vehiculos v = m.map(dto, Vehiculos.class);
        service.insert(v);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/{id}")
    public VehiculosDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), VehiculosDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<VehiculosDTO> listar() {
        return service.list().stream().map(v -> {
            ModelMapper m = new ModelMapper();
            return m.map(v, VehiculosDTO.class);
        }).collect(Collectors.toList());
    }
}