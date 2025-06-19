package org.example.service;

import org.example.model.Equipo;
import java.util.ArrayList;
import java.util.List;

public class EquipoService {
    private static final List<Equipo> equipos = new ArrayList<>();
    private static final Object lock = new Object();

    public void agregarEquipo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        Equipo nuevoEquipo = new Equipo(nombre);
        equipos.add(nuevoEquipo);
    }

    public List<Equipo> obtenerEquipos() {
        return equipos;
    }

    public void eliminarEquipo(String nombre) {
        equipos.remove(nombre);
    }

    public Equipo buscarEquipo(String nombre) {
        return equipos.stream().filter(equipo -> equipo.getNombre().equals(nombre)).findFirst().orElse(null);
    }

}
