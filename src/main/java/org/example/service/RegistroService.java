package org.example.service;

import org.example.model.Registro;
import java.util.ArrayList;
import java.util.List;

public class RegistroService {
    private static final List<Registro> registros = new ArrayList<>();

    public void agregarRegistro(String nombre, String email, String telefono) {
        Registro nuevoRegistro = new Registro(nombre, email, telefono);
        registros.add(nuevoRegistro);
    }

    public List<Registro> obtenerRegistros() {
        return new ArrayList<>(registros);
    }

    public void eliminarRegistro(String email) {
        registros.removeIf(registro -> registro.getEmail().equals(email));
    }

    public Registro buscarRegistro(String email) {
        return registros.stream()
                .filter(registro -> registro.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
