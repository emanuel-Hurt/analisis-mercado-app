package com.emanuelhurtcast.analisismercadoapp.repository;

import com.emanuelhurtcast.analisismercadoapp.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
