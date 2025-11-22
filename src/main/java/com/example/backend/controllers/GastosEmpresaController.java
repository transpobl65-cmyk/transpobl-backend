package com.example.backend.controllers;

import com.example.backend.dto.GastosEmpresaDTO;
import com.example.backend.model.GastosEmpresa;
import com.example.backend.model.Users;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.GastosEmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gastos-empresa")
public class GastosEmpresaController {

    @Autowired
    private GastosEmpresaService gastosEmpresaService;
    @Autowired
    private UserRepository userRepository;


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PostMapping
    public void registrar(@RequestBody GastosEmpresaDTO dto) {
        ModelMapper m = new ModelMapper();
        GastosEmpresa g = m.map(dto, GastosEmpresa.class);

        g.setVersion(null);  // Esto asegura que no se asigne una versión al crear un nuevo registro

        // ✅ 1️⃣ Obtén el usuario autenticado del contexto de seguridad
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // ✅ 2️⃣ Busca ese usuario en BD
        Users user = userRepository.findByUsername(username);

        // ✅ 3️⃣ Asigna el usuario al gasto antes de guardar
        if (user == null) {
            throw new RuntimeException("No se encontró el usuario autenticado en la BD.");
        }

        g.setCreadoPor(user);

        // ✅ 4️⃣ Guarda
        gastosEmpresaService.insert(g);
    }

  @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @PutMapping
    public void modificar(@RequestBody GastosEmpresaDTO dto) {
        ModelMapper m = new ModelMapper();
        GastosEmpresa g = m.map(dto, GastosEmpresa.class);
        gastosEmpresaService.insert(g);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        gastosEmpresaService.delete(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/{id}")
    public GastosEmpresaDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        return m.map(gastosEmpresaService.listarId(id), GastosEmpresaDTO.class);
    }


    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping
    public List<GastosEmpresaDTO> listar() {
        return gastosEmpresaService.list().stream().map(g -> {
            ModelMapper m = new ModelMapper();
            return m.map(g, GastosEmpresaDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'GERENTE')")
    @GetMapping("/buscar")
    public List<GastosEmpresaDTO> buscar(@RequestParam String term) {
        return gastosEmpresaService.list().stream()
                .filter(g -> g.getCategoria().toLowerCase().contains(term.toLowerCase())
                        || g.getDescripcion().toLowerCase().contains(term.toLowerCase()))
                .map(g -> {
                    ModelMapper m = new ModelMapper();
                    return m.map(g, GastosEmpresaDTO.class);
                })
                .collect(Collectors.toList());
    }



}