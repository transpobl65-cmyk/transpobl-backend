package com.example.backend.dto;



import com.example.backend.model.Solicitudes;
import com.example.backend.model.Users;
import com.example.backend.model.Vehiculos;

import java.time.LocalDate;

public class AsignacionesDTO {
    private Long id;
    private Solicitudes solicitud;
    private Vehiculos vehiculo;
    private Users conductor;
    private LocalDate inicio;
    private LocalDate fin;
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Users getConductor() {
        return conductor;
    }

    public void setConductor(Users conductor) {
        this.conductor = conductor;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
