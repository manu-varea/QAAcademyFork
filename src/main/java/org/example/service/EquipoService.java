package org.example.service;

import org.example.model.Equipo;
import java.util.ArrayList;
import java.util.List;

public class EquipoService {
    private static final List<Equipo> equipos = new ArrayList<>();

    public void agregarEquipo(String nombre, int id) {
        Equipo nuevoEquipo = new Equipo(nombre, id);
        equipos.add(nuevoEquipo);
    }

    public List<Equipo> obtenerEquipos() {
        return new ArrayList<>(equipos);
    }

    public void eliminarEquipo(int id) {
        equipos.removeIf(equipo -> equipo.getId() == id);
    }

    public Equipo buscarEquipo(int id) {
        return equipos.stream()
                .filter(equipo -> equipo.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
