package com.example.securityavanzado;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/publica")
    public String publica() {
        return "publica";
    }

    @GetMapping("/usuario")
    public String usuario() {
        return "usuario";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}