package com.emanuelhurtcast.analisismercadoapp.service;

import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaResponse;
import com.emanuelhurtcast.analisismercadoapp.mapper.EncuestaMapper;
import com.emanuelhurtcast.analisismercadoapp.mapper.PreguntaMapper;
import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import com.emanuelhurtcast.analisismercadoapp.repository.EncuestaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncuestaService {

    private final EncuestaRepository encuestaRepository;
    private final EncuestaMapper encuestaMapper;
    private final PreguntaMapper preguntaMapper;

    public EncuestaService(
            EncuestaRepository encuestaRepository,
            EncuestaMapper encuestaMapper,
            PreguntaMapper preguntaMapper) {

        this.encuestaRepository = encuestaRepository;
        this.encuestaMapper = encuestaMapper;
        this.preguntaMapper = preguntaMapper;
    }

    public EncuestaResponse crearEncuesta(EncuestaRequest encuestaRequest) {

        Encuesta encuesta = encuestaMapper.toEntity(encuestaRequest);

        encuestaRequest.getPreguntas().stream()
                .map(preguntaMapper::toEntity)
                .forEach(encuesta::addPregunta);


        return encuestaMapper.toResponse(encuestaRepository.save(encuesta));
    }

    public List<EncuestaResponse> listarEncuestas() {

        List<Encuesta> encuestas = encuestaRepository.findAll();

        return encuestas.stream()
                .map(encuestaMapper::toResponse)
                .toList();

    }

    public Optional<Encuesta> buscarEncuestaPorId(Long idEncuesta) {
        return encuestaRepository.findById(idEncuesta);
    }

    public void eliminarEncuestaPorId(Long idEncuesta) {
        encuestaRepository.deleteById(idEncuesta);
    }
}
