package com.emanuelhurtcast.analisismercadoapp.mapper;

import com.emanuelhurtcast.analisismercadoapp.dto.PreguntaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.PreguntaResponse;
import com.emanuelhurtcast.analisismercadoapp.model.Pregunta;
import com.emanuelhurtcast.analisismercadoapp.model.Respuesta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PreguntaMapper {

    public Pregunta toEntity(PreguntaRequest request) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPregunta(request.getTexto());
        pregunta.setTipoPregunta(request.getTipo());

        ArrayList<Respuesta> respuestas = new ArrayList<>();

        request.getOpciones().forEach(opcion -> {
            Respuesta respuesta = new Respuesta();
            respuesta.setContenido(opcion);
            respuesta.setPregunta(pregunta);
            respuestas.add(respuesta);
        });

        for  (Respuesta respuesta : respuestas) {
            pregunta.addRespuesta(respuesta);
        }

        return pregunta;
    }

    public PreguntaResponse toResponse(Pregunta pregunta) {
        PreguntaResponse response = new PreguntaResponse();
        response.setTexto(pregunta.getPregunta());

        return  response;
    }
}
