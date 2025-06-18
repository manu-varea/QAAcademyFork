package org.example.model;

import java.time.LocalDateTime;

public class Equipo {
    private String nombre;
    private int id;
    private LocalDateTime fechaRegistro;

    public Equipo(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.fechaRegistro = LocalDateTime.now();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
