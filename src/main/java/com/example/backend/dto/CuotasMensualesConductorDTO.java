package com.example.backend.dto;

import com.example.backend.model.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class CuotasMensualesConductorDTO {

    private Long id;
    private int anio;
    private int mes;
    private BigDecimal monto;
    private Users conductor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Users getConductor() {
        return conductor;
    }

    public void setConductor(Users conductor) {
        this.conductor = conductor;
    }
}
