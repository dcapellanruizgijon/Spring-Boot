package com.ejemplo.controller;

import com.ejemplo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/ejemplos")
    public String ejemplosThymeleaf(Model model) {
        // =========================================================================
        // DATOS PARA LAS VISTAS - EJEMPLOS VARIADOS
        // =========================================================================
        
        // Strings y números
        model.addAttribute("titulo", "Ejemplos de Thymeleaf");
        model.addAttribute("mensaje", "<strong>Texto</strong> con HTML");
        model.addAttribute("precio", 29.99);
        model.addAttribute("descuento", 0.15);
        model.addAttribute("activo", true);
        
        // Fechas
        model.addAttribute("fechaActual", LocalDateTime.now());
        
        // Listas
        List<String> frutas = Arrays.asList("Manzana", "Naranja", "Pera", "Uva");
        model.addAttribute("frutas", frutas);
        
        // Mapas
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("MX", "México");
        paises.put("AR", "Argentina");
        paises.put("CO", "Colombia");
        model.addAttribute("paises", paises);
        
        // Objetos complejos
        Usuario usuario = new Usuario(1L, "Ana García", "ana@ejemplo.com", 28);
        model.addAttribute("usuario", usuario);
        
        // Lista de objetos
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1L, "Carlos López", "carlos@email.com", 32),
            new Usuario(2L, "María Ruiz", "maria@email.com", 25),
            new Usuario(3L, "Pedro Díaz", "pedro@email.com", 41),
            new Usuario(4L, "Laura Méndez", "laura@email.com", 29)
        );
        model.addAttribute("usuarios", usuarios);
        
        // Para formularios
        model.addAttribute("generos", Arrays.asList("Masculino", "Femenino", "Otro"));
        model.addAttribute("intereses", Arrays.asList("Deportes", "Música", "Tecnología", "Viajes"));
        
        return "thymeleaf/ejemplos";
    }
}