package com.emanuelhurtcast.analisismercadoapp.dto;

public class PreguntaRequest {

    private String pregunta;
    private String tipoPregunta;

    public PreguntaRequest() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
}
