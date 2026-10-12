package com.emanuelhurtcast.analisismercadoapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EncuestaResponse {

    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean publicado;
    private ArrayList<PreguntaResponse> preguntas;

    public EncuestaResponse() {
        this.preguntas = new ArrayList<>();
    }

    public ArrayList<PreguntaResponse> getPreguntas() {
        return preguntas;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }
}
