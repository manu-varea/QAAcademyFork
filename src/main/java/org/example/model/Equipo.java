package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String fechaRegistro;
    private List<Jugador> jugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.fechaRegistro = LocalDateTime.now().toString();
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }


    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public Jugador getJugador(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    public void addJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", jugadores=" + jugadores.size() +
                '}';
    }
}
