package com.example.backend.services;

import com.example.backend.model.Role;
import com.example.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RolService {
    @Autowired
    private RoleRepository rR;

    public void insert(Role rol) {
        rR.save(rol);
    }

    public List<Role> list() {
        return rR.findAll();
    }

    public void delete(Long idRol) {
        rR.deleteById(idRol);
    }

    public Role listarId(Long idRol) {
        return rR.findById(idRol).orElse(new Role());
    }
}
