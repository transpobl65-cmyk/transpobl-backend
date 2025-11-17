package com.example.backend.controllers;


import com.example.backend.dto.ClientesDTO;
import com.example.backend.model.Clientes;
import com.example.backend.services.ClientesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody ClientesDTO dto) {
        ModelMapper m = new ModelMapper();
        Clientes c = m.map(dto, Clientes.class);
        service.insert(c);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody ClientesDTO dto) {
        ModelMapper m = new ModelMapper();
        Clientes c = m.map(dto, Clientes.class);
        service.insert(c);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }



    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/{id}")
    public ClientesDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(service.listarId(id), ClientesDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<ClientesDTO> listar() {
        return service.list().stream().map(c -> {
            ModelMapper m = new ModelMapper();
            return m.map(c, ClientesDTO.class);
        }).collect(Collectors.toList());
    }
}