package com.emanuelhurtcast.analisismercadoapp.controller;

import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaResponse;
import com.emanuelhurtcast.analisismercadoapp.service.EncuestaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/encuestas")
public class EncuestaController {

    private final EncuestaService encuestaService;

    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @GetMapping("/lista-encuestas")
    public String verEncuestas(Model model) {

        // MANDAR UNA LISTA DE OBJETO ENCUESTA
        EncuestaResponse encuestaResponse = new EncuestaResponse();
        encuestaResponse.setNombre("Encuesta de Prueba");
        encuestaResponse.setDescripcion("encuesta creada con fines de prueba");
        encuestaResponse.setPublicado(false);

        model.addAttribute("encuesta", encuestaResponse);

        return "encuestas";
    }

    @GetMapping("/formulario-encuesta")
    public String crearEncuesta(Model model) {

        EncuestaResponse encuestaResponse = new EncuestaResponse();
        model.addAttribute("encuestaNueva", encuestaResponse);
        return "formulario-encuesta";
    }

    @PostMapping("/nueva-encuesta")
    public String guardarEncuesta(
            @ModelAttribute("encuestaNueva") EncuestaRequest encuesta,
            BindingResult bindingResult,
            Model model) {

        // Lo guardamos
        EncuestaResponse encuestaResponse = encuestaService.crearEncuesta(encuesta);
        // Aun no lo hace, pero EncuestaResponse ya debe traer sus preguntas, ESTE AJUSTE EN EL SERVICE

        model.addAttribute("encuesta",encuestaResponse);

        return "encuestas";
    }
}
