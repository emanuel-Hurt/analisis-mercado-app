package com.emanuelhurtcast.analisismercadoapp.dto;

import java.util.ArrayList;
import java.util.List;

public class PreguntaRequest {

    private String texto;
    private String tipo;
    private List<String> opciones = new ArrayList<>();

    public PreguntaRequest() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}
