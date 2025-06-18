package org.example;

import org.example.controller.RegistroController;
import org.example.controller.EquipoController;
import org.example.controller.VistasController;

import static org.openqa.selenium.remote.http.Route.options;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        port(4567);
        
        // Configurar CORS
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type");
        });

        // Habilitar OPTIONS para CORS
        //options("/*", (request, response) -> {
        //    response.header("Access-Control-Allow-Origin", "*");
        //    response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        //    response.header("Access-Control-Allow-Headers", "Content-Type");
        //    return "OK";
        //});
        
        RegistroController registroController = new RegistroController();
        EquipoController equipoController = new EquipoController();
        VistasController vistasController = new VistasController();
        
        // Rutas para páginas HTML
        get("/", vistasController::getHomePage);
        get("/home", vistasController::getHomePage);
        get("/nombre_equipo", vistasController::getNombreEquipoPage);
        get("/nombre_equipo.html", vistasController::getNombreEquipoPage);
        get("/registro", vistasController::getRegistroPage);
        get("/registro.html", vistasController::getRegistroPage);
        get("/equipos", vistasController::getEquiposPage);
        get("/equipos.html", vistasController::getEquiposPage);
        get("/panel_admin", vistasController::getPanelAdminPage);
        get("/panel_admin.html", vistasController::getPanelAdminPage);
        
        // Endpoints para registros
        post("/api/registros", registroController::agregarRegistro);
        get("/api/registros", registroController::obtenerRegistros);
        delete("/api/registros/:email", registroController::eliminarRegistro);
        get("/api/registros/:email", registroController::buscarRegistro);
        
        // Endpoints para equipos
        post("/api/equipos", equipoController::agregarEquipo);
        get("/api/equipos", equipoController::obtenerEquipos);
        
        System.out.println("Servidor iniciado en http://localhost:4567");
    }

}
