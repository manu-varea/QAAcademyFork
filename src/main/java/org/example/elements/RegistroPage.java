package org.example.elements;

public class RegistroPage extends Pagina {
    private final String url = "http://localhost:4567/registro.html";
    private final String nombreField= "(//input[@id='nombre'])[1]";
    private final String emailField= "(//input[@id='email'])[1]";
    private final String telefonoField= "(//input[@id='telefono'])[1]";
    private final String registroButton= "(//button[normalize-space()='Registrar Jugador'])[1]";

    public String url() {
        return url;
    }

    public String getNombreField() {
        return nombreField;
    }

    public String getEmailField() {
        return emailField;
    }

    public String getTelefonoField() {
        return telefonoField;
    }

    public String getRegistroButton() {
        return registroButton;
    }

}
