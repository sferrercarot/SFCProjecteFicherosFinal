package com.samuel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListaEquipos {
    @JsonProperty("equipos")
    private List<Equipo> equipos;

    public ListaEquipos() {
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "ListaEquipos{" +
                "equipos=" + equipos +
                '}';
    }
}
