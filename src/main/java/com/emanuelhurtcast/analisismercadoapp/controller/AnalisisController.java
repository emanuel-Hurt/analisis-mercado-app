package com.emanuelhurtcast.analisismercadoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class AnalisisController {

    @GetMapping({"/home", "/"})
    public String verPaginaPrincipal(Model model) {
        model.addAttribute("activeMenu", "inicio");
        return "index";
    }
}
