package org.example.elements;

public class Navbar {
    private final String qaButton = "(//a[normalize-space()='QA Academy'])[1]";
    private final String homeButton = "(//a[normalize-space()='QA Academy'])[1]";
    private final String registroButton = "(//a[normalize-space()='Registro de Jugador'])[1]";
    private final String jugadoresButton = "(//a[normalize-space()='Ver Jugadores'])[1]";
    private final String equiposButton = "(//a[normalize-space()='Equipos asignados'])[1]";
    private final String[] dropdown = {
            "(//a[normalize-space()='Welcome QA Trainee'])[1]",
            "(//a[normalize-space()='Opciones'])[1]",
            "(//a[normalize-space()='Nombre Equipo'])[1]",
            "(//a[normalize-space()='Fecha Torneo'])[1]",
            "(//a[normalize-space()='Desloguear'])[1]"
    };


    public String getQaButton() {
        return qaButton;
    }

    public String getHomeButton() {
        return homeButton;
    }

    public String getRegistrationButton() {
        return registroButton;
    }

    public String getJugadoresButton() {
        return jugadoresButton;
    }

    public String getEquiposButton() {
        return equiposButton;
    }

    public String getDropdownButton() {
        return dropdown[0];
    }

    public String getDropdrownOptions() {
        return dropdown[1];
    }

    public String getDropdrownNombreEquipo() {
        return dropdown[2];
    }

    public String getDropdrownFechaTorneo() {
        return dropdown[3];
    }

    public String getDropdrownLogOut() {
        return dropdown[4];
    }
}
