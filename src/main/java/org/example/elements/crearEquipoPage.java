package org.example.elements;

public class crearEquipoPage extends Pagina {
    private final String url = "http://localhost:4567/crearEquipo.html";
    private final String nombreEquipoField = "(//input[@id='nombre_equipo'])[1]";
    private final String guardarNombreButton = "(//button[normalize-space()='Guardar Nombre'])[1]";
    private final String vistaPreviaButton = "(//button[normalize-space()='Vista Previa'])[1]";

    public String url(){
        return url;
    }

    public String getNombreEquipoField() {
        return nombreEquipoField;
    }

    public String getGuardarNombreButton() {
        return guardarNombreButton;
    }

    public String getVistaPreviaButton() {
        return vistaPreviaButton;
    }

}
