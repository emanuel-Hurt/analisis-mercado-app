package com.emanuelhurtcast.analisismercadoapp.repository;

import com.emanuelhurtcast.analisismercadoapp.model.TipoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPreguntaRepository extends JpaRepository<TipoPregunta, Long> {
}
