package com.samuel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Equipo {
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("fundado")
    private int fundado;

    @JsonProperty("estadio")
    private String estadio;

    @JsonProperty("ciudad")
    private String ciudad;

    @JsonProperty("entrenador")
    private String entrenador;

    @JsonProperty("jugadores")
    private List<Jugador> jugadores;

    public Equipo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFundado() {
        return fundado;
    }

    public void setFundado(int fundado) {
        this.fundado = fundado;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", fundado=" + fundado +
                ", estadio='" + estadio + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", entrenador='" + entrenador + '\'' +
                ", jugadores=" + jugadores +
                '}';
    }
}
