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
        
        System.out.println("Nombre: " + request.queryParams("nombre"));
        System.out.println("Email: " + request.queryParams("email"));
        System.out.println("Telefono: " + request.queryParams("telefono"));

        String nombre = request.queryParams("nombre");
        String email = request.queryParams("email");
        String telefono = request.queryParams("telefono");
        
        System.out.println("Agregando registro..." );
        jugadorService.registrarJugador(nombre, email, telefono);
        System.out.println("Registro Agregado..." );
        
        return gson.toJson(Map.of("status", "success", "message", "Jugador registrado exitosamente"));
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
