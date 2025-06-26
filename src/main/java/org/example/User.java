package org.example;

public class User {
    private String nombre;
    private String email;
    private String telefono;

    public User(String firstName, String email, String telefono) {
        this.nombre = firstName;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String[] getAll() { return new String[] { getNombre(), getEmail(), getTelefono()}; }

}
