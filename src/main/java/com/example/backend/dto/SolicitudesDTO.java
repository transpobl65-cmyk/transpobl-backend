package com.example.backend.dto;
import com.example.backend.model.Clientes;
import com.example.backend.model.Users;
import com.example.backend.model.Vehiculos;


import java.math.BigDecimal;
import java.time.LocalDate;

public class SolicitudesDTO {
    private Long id;
    private LocalDate fechaSalida;
    private String destino;
    private Clientes cliente;
    private Users usuario;

    private BigDecimal precio; // 💰 nuevo
    private Vehiculos vehiculo; // 🚛 nuevo

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
