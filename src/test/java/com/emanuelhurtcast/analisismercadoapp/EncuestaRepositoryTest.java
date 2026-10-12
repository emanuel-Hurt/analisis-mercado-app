package com.emanuelhurtcast.analisismercadoapp;

import com.emanuelhurtcast.analisismercadoapp.model.Encuesta;
import com.emanuelhurtcast.analisismercadoapp.repository.EncuestaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EncuestaRepositoryTest {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Test
    public void deberiaGuardarYRecuperarEncuesta() {

        // Given (dado un objeto de prueba)
        Encuesta encuesta = new Encuesta();
        encuesta.setNombre("encuesta de prueba");
        encuesta.setDescripcion("descripcion de prueba");
        encuesta.setPublicada(false);

        // When (cuando lo guardamos en la base de datos)
        Encuesta encuestaGuardada = encuestaRepository.save(encuesta);

        // Then (Entonces verificamos que se haya guardado y tenga un ID asignado)
        assertThat(encuestaGuardada).isNotNull();
        assertThat(encuestaGuardada.getEncuestaId()).isNotNull();

        // Opcional: Probar que se puede buscar por ID
        Optional<Encuesta> byId = encuestaRepository.findById(encuestaGuardada.getEncuestaId());
        assertThat(byId).isPresent();
    }
}
