package com.emanuelhurtcast.analisismercadoapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENCUESTA")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long encuestaId;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean publicada;

    @OneToMany(mappedBy = "encuesta",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();

    @PrePersist
    public void prePersistir() {
        this.fechaCreacion = LocalDateTime.now();
    }
    public Encuesta() {}

    // Método helper para mantener la consistencia bidireccional
    public void addPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
        pregunta.setEncuesta(this);
    }

    public void removePregunta(Pregunta pregunta) {
        preguntas.remove(pregunta);
        pregunta.setEncuesta(null);
    }

    public Long getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isPublicada() {
        return publicada;
    }

    public void setPublicada(boolean publicada) {
        this.publicada = publicada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
