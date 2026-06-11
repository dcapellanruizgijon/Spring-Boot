package com.example.demo.controlador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

@Controller
public class LanguageController {

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/cambiar-idioma")
    public String cambiarIdioma(@RequestParam("lang") String lang,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        System.out.println("=== CAMBIANDO IDIOMA ===");
        System.out.println("Idioma solicitado: " + lang);
        
        Locale locale;
        switch (lang) {
            case "en":
                locale = new Locale("en", "US");
                break;
            case "ca":
                locale = new Locale("ca", "ES");
                break;
            case "es":
            default:
                locale = new Locale("es", "ES");
                break;
        }
        
        localeResolver.setLocale(request, response, locale);
        
        // Verificar que se guardó correctamente
        Locale currentLocale = localeResolver.resolveLocale(request);
        System.out.println("Idioma establecido: " + currentLocale.getLanguage());
        System.out.println("Cookie lang debería estar configurada");
        
        String referer = request.getHeader("Referer");
        if (referer == null || referer.isEmpty()) {
            referer = "/";
        }
        
        return "redirect:" + referer;
    }
}