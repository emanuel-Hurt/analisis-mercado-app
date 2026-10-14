package com.emanuelhurtcast.analisismercadoapp.mapper;

import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaResponse;
import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import org.springframework.stereotype.Component;

@Component
public class EncuestaMapper {

    public Encuesta toEntity(EncuestaRequest encuestaRequest) {

        Encuesta encuestaEntity = new Encuesta();
        encuestaEntity.setNombre(encuestaRequest.getNombre());
        encuestaEntity.setDescripcion(encuestaRequest.getDescripcion());

        // Ver cómo agregar las preguntas

        return  encuestaEntity;
    }

    public EncuestaResponse toResponse(Encuesta encuesta) {

        EncuestaResponse encuestaResponse = new EncuestaResponse();
        encuestaResponse.setNombre(encuesta.getNombre());
        encuestaResponse.setDescripcion(encuesta.getDescripcion());

        return encuestaResponse;
    }
}
