package com.example.demo2;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ej4Controlador {

    private HashMap<String, String> nombreContrasena;

    public ej4Controlador(){
        this.nombreContrasena=new HashMap<String, String>();
        nombreContrasena.put("admin", "1234");
        nombreContrasena.put("recepcion", "4321");
    }
    public HashMap<String, String> getHashMap(){
        return this.nombreContrasena;
    }

    @GetMapping("/")
    public String aLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesaLogin(Model mod, HttpSession sesion, @RequestParam String nombre, @RequestParam String contrasena) {
        
        if(this.nombreContrasena.containsKey(nombre) && this.nombreContrasena.get("admin").equals(contrasena)){//pra que la contraseña sean string
            sesion.setAttribute("usuario", nombre);
            return "redirect:/dashboard";
        }else{
            mod.addAttribute("errorLogin", "El nombre de usuario o contraseña son incorrectos");
            return "login";
        }

    }

    @GetMapping("/dashboard")
    public String aDashboard() {
        return "dashboard";
    }
    
    
    
}
