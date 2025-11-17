package com.example.backend.controllers;

import com.example.backend.dto.AsignacionesDTO;
import com.example.backend.model.Asignaciones;
import com.example.backend.model.Solicitudes;
import com.example.backend.model.Users;
import com.example.backend.model.Vehiculos;
import com.example.backend.repository.SolicitudesRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VehiculosRepository;
import com.example.backend.services.AsignacionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionesController {

    @Autowired
    private AsignacionesService service;
    @Autowired
    private VehiculosRepository vehiculosRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SolicitudesRepository solicitudesRepository;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")

    @PostMapping
    public void registrar(@RequestBody AsignacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Asignaciones a = m.map(dto, Asignaciones.class);

        // ✅ Buscar el vehículo persistido
        Vehiculos vehiculo = vehiculosRepository.findById(dto.getVehiculo().getId()).orElse(null);
        a.setVehiculo(vehiculo);

        // ✅ Buscar el conductor persistido
        Users conductor = userRepository.findById(dto.getConductor().getId()).orElse(null);
        a.setConductor(conductor);

        // ✅ Buscar la solicitud persistida
        Solicitudes solicitud = solicitudesRepository.findById(dto.getSolicitud().getId()).orElse(null);
        a.setSolicitud(solicitud);

        // 🔹 Guardar finalmente la asignación
        service.insert(a);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody AsignacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Asignaciones a = m.map(dto, Asignaciones.class);
        service.insert(a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE','CONDUCTOR')")
    @GetMapping("/{id}")
    public AsignacionesDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), AsignacionesDTO.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE','CONDUCTOR')")
    @GetMapping
    public List<AsignacionesDTO> listar() {
        return service.list().stream().map(a -> {
            ModelMapper m = new ModelMapper();
            return m.map(a, AsignacionesDTO.class);
        }).collect(Collectors.toList());
    }


}