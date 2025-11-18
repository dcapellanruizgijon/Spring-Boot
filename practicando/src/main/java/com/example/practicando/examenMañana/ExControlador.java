package com.example.practicando.examenMañana;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class ExControlador {

    @GetMapping("/")
    public String redirigir() {
        return "login";//a que html voy?
        
    }
    
    
    @PostMapping("/entrar")
    public String login(HttpSession sesion, @RequestParam String nom, Model model) {

        //si es uno de estos nombres lo guarda en la sesion
        if(nom.equals("Cervantes") || nom.equals("Lorca") || nom.equals("Zafón")){
            sesion.setAttribute("autor", nom);
            sesion.setAttribute("gestor", new GestorLibros());
            return "examenMañana/index";
        }else{
            model.addAttribute("mensaje", "Error, con este nobre no puede entrar");
            return "examenMañana/login";
        }
    }
    


    @GetMapping("/libros")
    public String muestraLibros(HttpSession sesion, Model mod) {
        //si aun no hemos creado el gestor vamos a que lo cree
        if(sesion.getAttribute("gestor")==null){
            return "redirect:/";
        }
            GestorLibros gestor=(GestorLibros) sesion.getAttribute("gestor");

        if(gestor.getLibros().isEmpty()) {
        mod.addAttribute("mensaje", "Ahora mismo no hay libros");
        }
            mod.addAttribute("nombre", gestor.getLibros());
            mod.addAttribute("autoresContrasena", gestor.getAutorContraseña());//arrayAsociativo(hashmap)
            mod.addAttribute("arrayLibros", gestor.getLibros());//arrayAsociativo(hashmap)
            return "index";
        
        
    }
    


    
}
