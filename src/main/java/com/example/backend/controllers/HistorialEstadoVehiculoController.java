package com.example.backend.controllers;

import com.example.backend.dto.HistorialEstadoVehiculoDTO;
import com.example.backend.model.HistorialEstadoVehiculo;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.HistorialEstadoVehiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historial-vehiculo")
public class HistorialEstadoVehiculoController {

    @Autowired
    private HistorialEstadoVehiculoService service;
    @Autowired
    private UserRepository userRepository;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody HistorialEstadoVehiculoDTO dto) {
        ModelMapper m = new ModelMapper();
        HistorialEstadoVehiculo h = m.map(dto, HistorialEstadoVehiculo.class);
        service.insert(h);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody HistorialEstadoVehiculoDTO dto) {
        ModelMapper m = new ModelMapper();
        HistorialEstadoVehiculo h = m.map(dto, HistorialEstadoVehiculo.class);
        service.insert(h);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/{id}")
    public HistorialEstadoVehiculoDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), HistorialEstadoVehiculoDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<HistorialEstadoVehiculoDTO> listar() {
        return service.list().stream().map(h -> {
            ModelMapper m = new ModelMapper();
            return m.map(h, HistorialEstadoVehiculoDTO.class);
        }).collect(Collectors.toList());
    }
}