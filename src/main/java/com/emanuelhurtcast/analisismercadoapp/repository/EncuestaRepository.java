package com.emanuelhurtcast.analisismercadoapp.repository;

import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
}
