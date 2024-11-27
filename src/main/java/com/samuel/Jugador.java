package com.samuel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Jugador {
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("posicion")
    private String posicion;

    @JsonProperty("edad")
    private int edad;

    @JsonProperty("nacionalidad")
    private String nacionalidad;

    @JsonProperty("imagen")
    private String imagen;

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                ", edad=" + edad +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
