package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cuotas_mensuales_conductor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CuotasMensualesConductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuotasconductor")
    private Long id;

    private int anio;
    private int mes;
    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "conductor_usuario_id", nullable = false)
    private Users conductor;

    // Getters y Setters

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
