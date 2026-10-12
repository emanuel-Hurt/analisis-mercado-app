package com.emanuelhurtcast.analisismercadoapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RESPUESTA")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long respuestaId;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private int frecuencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preguntaId", nullable = false)
    private Pregunta pregunta;

    public Respuesta() {
    }

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
