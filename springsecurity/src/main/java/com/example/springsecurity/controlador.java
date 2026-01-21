package com.example.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//@Controller se usa para aplicaciones web tradicionales que devuelven vistas HTML, 
//mientras que @RestController es una especialización para APIs RESTful que devuelven datos (JSON/XML) directamente
import org.springframework.web.bind.annotation.PostMapping;


@Controller  
public class controlador {
    
    @GetMapping("/")
    public String home(){
        return "index";
    }


    
    
}
