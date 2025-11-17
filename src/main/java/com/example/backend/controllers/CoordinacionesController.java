package com.example.backend.controllers;

import com.example.backend.dto.CoordinacionesDTO;
import com.example.backend.model.Coordinaciones;
import com.example.backend.services.CoordinacionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coordinaciones")
public class CoordinacionesController {

    @Autowired
    private CoordinacionesService service;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody CoordinacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Coordinaciones c = m.map(dto, Coordinaciones.class);
        service.insert(c);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody CoordinacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Coordinaciones c = m.map(dto, Coordinaciones.class);
        service.insert(c);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public CoordinacionesDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), CoordinacionesDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<CoordinacionesDTO> listar() {
        return service.list().stream().map(c -> {
            ModelMapper m = new ModelMapper();
            return m.map(c, CoordinacionesDTO.class);
        }).collect(Collectors.toList());
    }
}