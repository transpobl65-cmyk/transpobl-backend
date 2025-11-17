package com.example.backend.controllers;

import com.example.backend.dto.AsignacionesDTO;
import com.example.backend.dto.GastosConductorDTO;
import com.example.backend.model.GastosConductor;
import com.example.backend.services.AsignacionesService;
import com.example.backend.services.GastosConductorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gastos-conductor")
public class GastosConductorController {

    @Autowired
    private GastosConductorService service;
    @Autowired
    private AsignacionesService asignacionesService;

    @PreAuthorize("hasAnyAuthority('CONDUCTOR')")
    @PostMapping
    public void registrar(@RequestBody GastosConductorDTO dto) {
        ModelMapper m = new ModelMapper();
        GastosConductor g = m.map(dto, GastosConductor.class);

        // ✅ Buscar la asignación persistida
        var asignacion = asignacionesService.listarId(dto.getAsignacion().getId());
        g.setAsignacion(asignacion);

        // ✅ Buscar el conductor real desde BD (por username)
        var conductor = asignacion.getConductor(); // ya viene en la asignación
        g.setConductor(conductor);

        // ✅ Autocompletar placa desde la asignación
        if (asignacion.getVehiculo() != null) {
            g.setPlaca(asignacion.getVehiculo().getPlaca());
        }

        service.insert(g);
    }


    @PreAuthorize("hasAnyAuthority('CONDUCTOR')")
    @PutMapping
    public void modificar(@RequestBody GastosConductorDTO dto) {
        ModelMapper m = new ModelMapper();
        GastosConductor g = m.map(dto, GastosConductor.class);
        service.insert(g);
    }

    @PreAuthorize("hasAnyAuthority('CONDUCTOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('CONDUCTOR')")
    @GetMapping("/{id}")
    public GastosConductorDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), GastosConductorDTO.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE', 'CONDUCTOR')")
    @GetMapping
    public List<GastosConductorDTO> listar() {
        return service.list().stream().map(g -> {
            ModelMapper m = new ModelMapper();
            return m.map(g, GastosConductorDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('CONDUCTOR')")
    @GetMapping("/mis-gastos/{username}")
    public List<GastosConductorDTO> listarPorConductor(@PathVariable String username) {
        return service.list().stream()
                .filter(g -> g.getConductor().getUsername().equalsIgnoreCase(username))
                .map(g -> {
                    ModelMapper m = new ModelMapper();
                    return m.map(g, GastosConductorDTO.class);
                })
                .collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('CONDUCTOR')")
    @GetMapping("/mis-asignaciones")
    public List<AsignacionesDTO> obtenerMisAsignaciones() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("🟢 Usuario autenticado: " + username);

        return asignacionesService.list().stream()
                .filter(a -> a.getConductor() != null &&
                        a.getConductor().getUsername() != null &&
                        a.getConductor().getUsername().equalsIgnoreCase(username))
                .map(a -> {
                    ModelMapper m = new ModelMapper();
                    return m.map(a, AsignacionesDTO.class);
                })
                .collect(Collectors.toList());
    }



}