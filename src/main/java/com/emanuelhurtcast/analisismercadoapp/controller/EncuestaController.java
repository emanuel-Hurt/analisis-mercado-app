package com.emanuelhurtcast.analisismercadoapp.controller;

import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.EncuestaResponse;
import com.emanuelhurtcast.analisismercadoapp.dto.PreguntaRequest;
import com.emanuelhurtcast.analisismercadoapp.dto.RespuestaRequest;
import com.emanuelhurtcast.analisismercadoapp.service.EncuestaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

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
//        EncuestaResponse encuestaResponse = new EncuestaResponse();
//        encuestaResponse.setNombre("Encuesta de Prueba");
//        encuestaResponse.setDescripcion("encuesta creada con fines de prueba");
//        encuestaResponse.setPublicado(false);

        List<EncuestaResponse> encuestasResponse = encuestaService.listarEncuestas();

        model.addAttribute("encuestas", encuestasResponse);

        model.addAttribute("activeMenu", "encuestas");

        return "encuestas";
    }

    @GetMapping("/formulario-encuesta")
    public String crearEncuesta(Model model) {

        EncuestaResponse encuestaResponse = new EncuestaResponse();
        model.addAttribute("encuestaNueva", encuestaResponse);
        model.addAttribute("activeMenu", "encuestas");
        return "formulario-encuesta";
    }

    @PostMapping("/nueva-encuesta")
    public String guardarEncuesta(
            @ModelAttribute("encuestaNueva") EncuestaRequest encuesta,
            BindingResult bindingResult,
            Model model) {

        // Lo guardamos
        EncuestaResponse encuestaResponse = encuestaService.crearEncuesta(encuesta);
        // Aun no lo hace pero... EncuestaResponse ya debe traer sus preguntas, ESTE AJUSTE EN EL SERVICE

        System.out.println(encuesta.getNombre());
        for (PreguntaRequest preguntaRequest : encuesta.getPreguntas()) {
            System.out.println(preguntaRequest.getTexto());
            System.out.println("Su tipo: " + preguntaRequest.getTipo());
            System.out.println("ESTAS SON SUS OPCIONES:");
            for (String opcion: preguntaRequest.getOpciones()) {
                System.out.println(opcion);
            }
        }

        model.addAttribute("encuesta",encuestaResponse);

        model.addAttribute("activeMenu", "encuestas");

        return "encuestas";
    }
}
