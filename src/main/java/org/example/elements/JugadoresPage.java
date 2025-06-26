package org.example.elements;

public class JugadoresPage extends Pagina {
    private final String url = "http://localhost:4567/jugadores";
    private final String listaJugadores = "(//div[@class='panel-body'])[1]";
    private final String tituloColumna = "//*[@id=\"jugadores-container\"]/table/thead/tr/th[" /* 1]" */;
    private final String datoColumna = "//*[@id=\"jugadores-container\"]/table/tbody/tr[1]/td["  /* 1]" */;
    private final String botonVer = "(//button[@class='btn btn-info btn-sm'][normalize-space()='Ver'])["  /* 1]" */;
    private final String botonEliminar = "(//button[@class='btn btn-danger btn-sm'][normalize-space()='Eliminar'])["  /* 1]" */;
    private final String cardJugador = "(//div[@class='modal-body'])[1]";
    private final String botonCard = "(//button[@type='button'][normalize-space()='Cerrar'])[1]";

    public String url() {
        return url;
    }

    public String getListaJugadores() {
        return listaJugadores;
    }

    public String getTituloColumna(int i) {
        return tituloColumna+i+"]";
    }

    public String getDatoColumna(int i) {
        return datoColumna+i+"]";
    }

    public String getBotonVer(int i) {
        return botonVer+i+"]";
    }

    public String getBotonEliminar(int i) {
        return botonEliminar+i+"]";
    }

    public String getCardJugador() {
        return cardJugador;
    }

    public String getBotonCard() {
        return botonCard;
    }
}
