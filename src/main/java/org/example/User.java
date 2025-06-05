package org.example;

public class User {
    private String nombre;
    private String telefono;
    private String email;
    private String discordUser;
    private String ign;
    private String nivel;
    private String rolPrincipal;
    private String rolSecundario;
    private String pais;

    public User(String nombre, String telefono, String email, String discordUser, String ign, String nivel, String rolPrincipal, String rolSecundario, String pais) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.discordUser = discordUser;
        this.ign = ign;
        this.nivel = nivel;
        this.rolPrincipal = rolPrincipal;
        this.rolSecundario = rolSecundario;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(String discordUser) {
        this.discordUser = discordUser;
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRolPrincipal() {
        return rolPrincipal;
    }

    public void setRolPrincipal(String rolPrincipal) {
        this.rolPrincipal = rolPrincipal;
    }

    public String getRolSecundario() {
        return rolSecundario;
    }

    public void setRolSecundario(String rolSecundario) {
        this.rolSecundario = rolSecundario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
