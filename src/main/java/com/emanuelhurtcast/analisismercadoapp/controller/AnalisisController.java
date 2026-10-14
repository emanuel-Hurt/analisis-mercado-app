package com.emanuelhurtcast.analisismercadoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analisis-datos")
public class AnalisisController {

    @GetMapping("/home")
    public String verPaginaPrincipal() {
        return "index";
    }
}
