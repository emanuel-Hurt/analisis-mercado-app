package com.emanuelhurtcast.analisismercadoapp.dto;

import java.util.List;

public class EncuestaRequest {

    private String nombre;
    private String descripcion;
    private String pregunta;

    public EncuestaRequest() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
