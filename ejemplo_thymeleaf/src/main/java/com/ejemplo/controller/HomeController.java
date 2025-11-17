package com.ejemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Bienvenido a Spring Boot");
        model.addAttribute("mensaje", "Esta es una demostración completa de Spring Boot");
        model.addAttribute("tecnologias", 
            java.util.Arrays.asList("Spring Boot", "Thymeleaf", "Sesiones", "Cookies"));
        return "index";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}