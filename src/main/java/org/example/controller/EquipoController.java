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
        
        String nombre = request.queryParams("nombre_equipo");
        int id = Integer.parseInt(request.queryParams("equipo_id"));
        
        equipoService.agregarEquipo(nombre, id);
        
        return gson.toJson(Map.of(
            "status", "success",
            "message", "Equipo agregado exitosamente",
            "equipo", Map.of("nombre", nombre, "id", id)
        ));
    }

    public Object obtenerEquipos(Request request, Response response) {
        response.type("application/json");
        List<Equipo> equipos = equipoService.obtenerEquipos();
        return gson.toJson(equipos);
    }
}
