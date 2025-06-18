package org.example.service;

import org.example.model.Jugador;
import java.util.ArrayList;
import java.util.List;

public class JugadorService {
    private static final List<Jugador> jugadores = new ArrayList<>();

    public void registrarJugador(String nombre, String email, String telefono) {
        Jugador nuevoJugador = new Jugador(nombre, email, telefono);
        jugadores.add(nuevoJugador);
    }

    public List<Jugador> obtenerJugadores() {
        return new ArrayList<>(jugadores);
    }

    public void eliminarJugador(String email) {
        jugadores.removeIf(jugador -> jugador.getEmail().equals(email));
    }

    public Jugador buscarJugador(String email) {
        return jugadores.stream()
                .filter(jugador -> jugador.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
