package com.example.dialogos;

public class Cancion {

    private String nombre, duracion, linkSpotify;

    public Cancion(String nombre, String duracion, String linkSpotify) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.linkSpotify = linkSpotify;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getLinkSpotify() {
        return linkSpotify;
    }
}
