package com.example.examen;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class controlador {
    
    

    @GetMapping("/")
    public String mostrarIndex(Model mod, HttpSession sesion) {

        if (sesion.getAttribute("gestor")==null) {

            sesion.setAttribute("gestor", new gestor());
        }
        gestor gest=(gestor)sesion.getAttribute("gestor");
        mod.addAttribute("listaLaptops", gest.getListaLaptops());
        mod.addAttribute("listaPcs", gest.getListaPcs());

        

        // if(tiempo.isEmpty()){
            
        //     Cookie cookie = new Cookie("tiempo", "0");
            

        // }else{
        //     Date fechaActual=new Date();

        //     int t=(int) tiempo;
        //     int diferencia=fechaActual.getTime();


        // }
        
        return "index";

    }

    @GetMapping("/nuevoOrdenador")
    public String getMethodName() {
        return "anadir";
    }
    

    @PostMapping("/nuevaLaptop")
    public String procesaLaptop(HttpSession sesion, Model mod, @RequestParam String id, @RequestParam int precio) {
        laptop l=new laptop(id, precio, "Cpu", "examen\\\\src\\\\main\\\\resources\\\\templates\\\\portatil.jpg");
        gestor gest=(gestor) sesion.getAttribute("gestor");
        gest.getListaLaptops().add(l);
        return "redirect:/";
    }

    @PostMapping("/nuevaPc")
    public String procesaPC(HttpSession sesion, Model mod, @RequestParam String id, @RequestParam int precio) {
        pc p=new pc(id, precio, "Cpu", "examen\\\\src\\\\main\\\\resources\\\\templates\\\\portatil.jpg");
        gestor gest=(gestor) sesion.getAttribute("gestor");
        gest.getListaPcs().add(p);
        return "redirect:/";
    }
    


    


    
}
