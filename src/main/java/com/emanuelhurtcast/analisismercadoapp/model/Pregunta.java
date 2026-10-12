package com.emanuelhurtcast.analisismercadoapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PREGUNTA")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;

    @Column(nullable = false)
    private String pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encuestaId", nullable = false)
    private Encuesta encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoId", nullable = false)
    private TipoPregunta tipoPregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variableId", nullable = false)
    private VariableDeEstudio variableDeEstudio;

    public Pregunta() {
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public VariableDeEstudio getVariableDeEstudio() {
        return variableDeEstudio;
    }

    public void setVariableDeEstudio(VariableDeEstudio variableDeEstudio) {
        this.variableDeEstudio = variableDeEstudio;
    }
}
