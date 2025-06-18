package org.example.controller;

import org.example.service.RegistroService;
import org.example.model.Registro;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

public class RegistroController {
    private final RegistroService registroService = new RegistroService();
    private final Gson gson = new Gson();

    public Object agregarRegistro(Request request, Response response) {
        response.type("application/json");
        
        System.out.println("Nombre: " + request.queryParams("nombre"));
        System.out.println("Email: " + request.queryParams("email"));
        System.out.println("Telefono: " + request.queryParams("telefono"));
        
        String nombre = request.queryParams("nombre");
        String email = request.queryParams("email");
        String telefono = request.queryParams("telefono");
        
        System.out.println("Agregando registro..." );
        registroService.agregarRegistro(nombre, email, telefono);
        System.out.println("Registro Agregado..." );
        
        return gson.toJson(Map.of("status", "success", "message", "Registro agregado exitosamente"));
    }

    public Object obtenerRegistros(Request request, Response response) {
        response.type("application/json");
        List<Registro> registros = registroService.obtenerRegistros();
        return gson.toJson(registros);
    }

    public Object eliminarRegistro(Request request, Response response) {
        response.type("application/json");
        String email = request.params("email");
        registroService.eliminarRegistro(email);
        return gson.toJson(Map.of("status", "success", "message", "Registro eliminado exitosamente"));
    }

    public Object buscarRegistro(Request request, Response response) {
        response.type("application/json");
        String email = request.params("email");
        Registro registro = registroService.buscarRegistro(email);
        return gson.toJson(registro);
    }
}
