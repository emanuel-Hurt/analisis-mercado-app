package com.emanuelhurtcast.analisismercadoapp.repository;

import com.emanuelhurtcast.analisismercadoapp.model.VariableDeEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableDeEstudioRepository extends JpaRepository<VariableDeEstudio, Long> {
}
