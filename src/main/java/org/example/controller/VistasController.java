package org.example.controller;

import spark.Request;
import spark.Response;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;


public class VistasController {
    public Object getHomePage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/HomePage.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getNombreEquipoPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/crearEquipo.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getRegistroPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/crear_registro.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getEquiposPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/equipos.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getJugadoresPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/jugadores.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getPanelAdminPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/panel_admin.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getEquipoCreadoPage(Request request, Response response) {
        try {
            InputStream input = getClass().getResourceAsStream("/TorneoHTML/equipo_creado.html");
            if (input == null) {
                throw new FileNotFoundException("Recurso no encontrado");
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }
}
