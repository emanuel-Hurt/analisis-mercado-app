package com.emanuelhurtcast.analisismercadoapp.controller;

import com.emanuelhurtcast.analisismercadoapp.service.PresentacionDatosService;
import com.emanuelhurtcast.analisismercadoapp.service.PresentacionDinamicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.tablesaw.api.Table;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AnalisisController {

    private Table datosCargados;
    private final PresentacionDatosService presentacionDatosService;
    private final PresentacionDinamicaService presentacionDinamicaService;

    public  AnalisisController(PresentacionDatosService presentacionDatosService,
                               PresentacionDinamicaService dinamicaService) {

        this.presentacionDatosService = presentacionDatosService;
        this.presentacionDinamicaService = dinamicaService;
    }

    @GetMapping({"/home", "/"})
    public String verPaginaPrincipal(Model model) {
        model.addAttribute("activeMenu", "inicio");
        return "index";
    }

    @GetMapping("/fuente-datos")
    public String cargarDatos(Model model) {

        model.addAttribute("activeMenu", "datos");

        return "subir-datos";
    }

    @PostMapping("/upload")
    public String manejarSubidaArchivo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Por favor selecciona un archivo válido.");
            return "redirect:/fuente-datos";
        }

        try {

            datosCargados = Table.read().csv(file.getInputStream());

            redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado y procesado con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar el archivo: " + e.getMessage());
        }

        return "redirect:/analisis-datos";
    }

    @GetMapping("/analisis-datos")
    public String analizarDatos(Model model) {

        // 1. Primeras 10 filas para la vista inicial
        List<Map<String, Object>> primerasDiez = presentacionDatosService.obtenerPrimerasDiezFilas(datosCargados);
        model.addAttribute("columnas", datosCargados.columnNames());
        model.addAttribute("filasDiez", primerasDiez);

        model.addAttribute("activeMenu", "datos");

        return "presentacion-datos";
    }

    @GetMapping("/calcular")
    @ResponseBody
    public ResponseEntity<?> calcular(
            @RequestParam String operacion,
            @RequestParam String colIndex,
            @RequestParam(required = false) String col2) {



        try {
            System.out.println("operacion: " + operacion + "columna: " + colIndex);
            Object resultado = presentacionDinamicaService.procesarOperacion(datosCargados, operacion, colIndex, col2);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la operación: " + e.getMessage());
        }
    }
}
