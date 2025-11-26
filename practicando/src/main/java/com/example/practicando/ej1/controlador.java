package com.example.practicando.ej1;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class controlador {
    
    public ArrayList<Tarea> tareas=new ArrayList<>();


    @GetMapping("/")
    public String redireccion(HttpSession sesion, Model model) {

        model.addAttribute("arrayTareas", tareas);

        return "ej1/index.html";
    }
    

    @GetMapping("/verTarea")
    public String getMethodName(@RequestParam String nombre) {
        return new String();
    }
    
}
