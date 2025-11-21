package com.example.backend.model;

import jakarta.persistence.*;
@Entity
@Table(
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rol"})}
)

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
