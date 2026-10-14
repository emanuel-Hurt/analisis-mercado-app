package com.emanuelhurtcast.analisismercadoapp.service;

import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaResponse;
import com.emanuelhurtcast.analisismercadoapp.mapper.EncuestaMapper;
import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import com.emanuelhurtcast.analisismercadoapp.repository.EncuestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncuestaService {

    private final EncuestaRepository encuestaRepository;
    private final EncuestaMapper encuestaMapper;

    public EncuestaService(EncuestaRepository encuestaRepository, EncuestaMapper encuestaMapper) {
        this.encuestaRepository = encuestaRepository;
        this.encuestaMapper = encuestaMapper;
    }

    public EncuestaResponse crearEncuesta(EncuestaRequest encuestaRequest) {
        Encuesta encuesta = encuestaMapper.toEntity(encuestaRequest);
        return encuestaMapper.toResponse(encuestaRepository.save(encuesta));
    }

    public List<Encuesta> listarEncuestas() {
        return encuestaRepository.findAll();
    }

    public Optional<Encuesta> buscarEncuestaPorId(Long idEncuesta) {
        return encuestaRepository.findById(idEncuesta);
    }

    public void eliminarEncuestaPorId(Long idEncuesta) {
        encuestaRepository.deleteById(idEncuesta);
    }
}
