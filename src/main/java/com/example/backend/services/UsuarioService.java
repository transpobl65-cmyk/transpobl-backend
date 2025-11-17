package com.example.backend.services;
import com.example.backend.model.Users;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UserRepository uR;


    public void insert(Users usuario) {
        uR.save(usuario);
    }


    public List<Users> list() {
        return uR.findAll();
    }


    public void delete(Long idUsuario) {
        uR.deleteById(idUsuario);
    }


    public Users listarId(Long idUsuario) {
        return uR.findById(idUsuario).orElse(new Users());
    }

}
