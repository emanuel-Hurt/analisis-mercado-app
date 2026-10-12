package com.emanuelhurtcast.analisismercadoapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VARIABLE_DE_ESTUDIO")
public class VariableDeEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variableId;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    public VariableDeEstudio() {
    }

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
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
