package org.example.controller;

import spark.Request;
import spark.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VistasController {
    public Object getHomePage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/HomePage.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getNombreEquipoPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/crearEquipo.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getRegistroPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/crear_registro.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getEquiposPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/equipos.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getJugadoresPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/jugadores.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getPanelAdminPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/panel_admin.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }

    public Object getEquipoCreadoPage(Request request, Response response) {
        try {
            String path = getClass().getResource("/TorneoHTML/equipo_creado.html").toURI().getPath();
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar la página";
        }
    }
}
