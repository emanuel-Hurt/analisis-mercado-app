package com.emanuelhurtcast.analisismercadoapp.service;

import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import com.emanuelhurtcast.analisismercadoapp.repository.EncuestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncuestaService {

    private final EncuestaRepository encuestaRepository;

    public EncuestaService(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }

    public Encuesta crearEncuesta(Encuesta encuesta) {
        return encuestaRepository.save(encuesta);
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
