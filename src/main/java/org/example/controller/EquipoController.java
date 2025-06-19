package org.example.controller;

import org.example.service.EquipoService;
import org.example.model.Equipo;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

public class EquipoController {
    private final EquipoService equipoService = new EquipoService();
    private final Gson gson = new Gson();

    public Object agregarEquipo(Request request, Response response) {
        response.type("application/json");
        
        try {
            // Intentar obtener datos de JSON
            String body = request.body();
            if (body == null || body.isEmpty()) {
                throw new IllegalArgumentException("No se recibieron datos");
            }
            
            Map<String, String> datos = gson.fromJson(body, Map.class);
            String nombre = datos.get("nombre_equipo");

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("Nombre del equipo es requerido");
            }
            

            equipoService.agregarEquipo(nombre);
            
            return gson.toJson(Map.of(
                "status", "success",
                "message", "Equipo agregado exitosamente",
                "equipo", Map.of("nombre", nombre)
            ));
            
        } catch (IllegalArgumentException e) {
            response.status(400);
            return gson.toJson(Map.of(
                "status", "error",
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(Map.of(
                "status", "error",
                "message", "Error interno del servidor",
                "error", e.getMessage()
            ));
        }
    }

    public Object obtenerEquipos(Request request, Response response) {
        response.type("application/json");
        List<Equipo> equipos = equipoService.obtenerEquipos();
        return gson.toJson(equipos);
    }

}
