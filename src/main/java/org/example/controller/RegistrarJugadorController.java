package org.example.controller;

import org.example.service.JugadorService;
import org.example.model.Jugador;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

public class RegistrarJugadorController {
    private final JugadorService jugadorService = new JugadorService();
    private final Gson gson = new Gson();

    public Object registrarJugador(Request request, Response response) {
        response.type("application/json");
        
        // Leer el cuerpo de la petición como JSON
        String body = request.body();
        Jugador jugador = gson.fromJson(body, Jugador.class);
        
        System.out.println("Agregando registro..." );
        jugadorService.registrarJugador(jugador.getNombre(), jugador.getEmail(), jugador.getTelefono());
        System.out.println("Registro Agregado..." );
        
        // Devolver el jugador creado con su nombre como identificador
        return gson.toJson(Map.of("nombre", jugador.getNombre(), "email", jugador.getEmail(), "telefono", jugador.getTelefono()));
    }

    public Object obtenerJugadores(Request request, Response response) {
        response.type("application/json");
        List<Jugador> jugadores = jugadorService.obtenerJugadores();
        return gson.toJson(jugadores);
    }

    public Object eliminarJugador(Request request, Response response) {
        response.type("application/json");
        String email = request.params("email");
        jugadorService.eliminarJugador(email);
        return gson.toJson(Map.of("status", "success", "message", "Jugador eliminado exitosamente"));
    }

    public Object buscarJugador(Request request, Response response) {
        response.type("application/json");
        String email = request.params("email");
        Jugador jugador = jugadorService.buscarJugador(email);
        return gson.toJson(jugador);
    }
}
