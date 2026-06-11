package com.example.demo.controlador;

import com.example.demo.ServiciosImplementacion.WeatherService;
import com.example.demo.clases.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clima")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping
    public String mostrarClima(@RequestParam(defaultValue = "Madrid") String ciudad,
                               Model model,
                               HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        
        String clima = weatherService.getWeatherForCity(ciudad);
        String recomendacion = weatherService.getWorkoutRecommendation(clima);
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("clima", clima);
        model.addAttribute("recomendacion", recomendacion);
        
        return "clima";
    }
}