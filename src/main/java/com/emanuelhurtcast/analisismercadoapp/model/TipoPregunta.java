package com.emanuelhurtcast.analisismercadoapp.model;

import com.emanuelhurtcast.analisismercadoapp.model.enums.TipoDePregunta;
import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_PREGUNTA")
public class TipoPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoId;

    @Enumerated(EnumType.STRING)
    private TipoDePregunta tipo;

    public TipoPregunta() {}

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public TipoDePregunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoDePregunta tipo) {
        this.tipo = tipo;
    }
}
