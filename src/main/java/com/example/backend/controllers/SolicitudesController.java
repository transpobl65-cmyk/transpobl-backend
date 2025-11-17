package com.example.backend.controllers;
import com.example.backend.dto.SolicitudesDTO;
import com.example.backend.model.Clientes;
import com.example.backend.model.Solicitudes;
import com.example.backend.model.Users;
import com.example.backend.repository.ClientesRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VehiculosRepository;
import com.example.backend.services.SolicitudesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
    private SolicitudesService service;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private VehiculosRepository vehiculosRepository;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody SolicitudesDTO dto) {
        ModelMapper m = new ModelMapper();
        Solicitudes s = m.map(dto, Solicitudes.class);

        // 🔹 Buscar el cliente persistido
        if (dto.getCliente() != null && dto.getCliente().getId() != null) {
            Clientes cliente = clientesRepository.findById(dto.getCliente().getId()).orElse(null);
            s.setCliente(cliente);
        }

        // 🔹 Buscar el usuario persistido
        if (dto.getUsuario() != null && dto.getUsuario().getUsername() != null) {
            String username = dto.getUsuario().getUsername();
            Users user = userRepository.findByUsername(username);
            s.setUsuario(user);
        }

        // 🔹 Buscar el vehículo persistido
        if (dto.getVehiculo() != null && dto.getVehiculo().getId() != null) {
            var vehiculo = vehiculosRepository.findById(dto.getVehiculo().getId()).orElse(null);
            s.setVehiculo(vehiculo);
        }

        // 💰 El campo precio ya llega directo del DTO
        service.insert(s);
    }




    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody SolicitudesDTO dto) {
        ModelMapper m = new ModelMapper();
        Solicitudes s = m.map(dto, Solicitudes.class);
        service.insert(s);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/{id}")
    public SolicitudesDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), SolicitudesDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<SolicitudesDTO> listar() {
        return service.list().stream().map(s -> {
            ModelMapper m = new ModelMapper();
            return m.map(s, SolicitudesDTO.class);
        }).collect(Collectors.toList());
    }
}