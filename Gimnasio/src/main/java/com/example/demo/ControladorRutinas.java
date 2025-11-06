package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import clases.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRutinas {

    // Array interno para simular base de datos
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    
    // Constructor - se ejecuta al crear el controlador
    public ControladorRutinas() {
        // Crear usuarios de prueba
        usuarios.add(new Usuario("Diego", "1234", 25, 60.0, 165.0));
        usuarios.add(new Usuario("carlos", "abcd", 30, 75.0, 180.0));
    }

    // Página principal - si no hay usuario va al login
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // Obtener usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        // Si no hay usuario en sesión, redirigir al login
        if (usuario == null) {
            return "login";
        }
        
        // Si hay usuario, pasar datos al modelo y mostrar la pagina index (para acceder a usuario desde index accederemos a "usuario")
        model.addAttribute("usuario", usuario);
        return "index";
    }

    
    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    
    
    // Procesar el formulario de login
    @PostMapping("/login")
    public String procesarLogin(HttpSession session, Model model, @RequestParam String nom,  @RequestParam String cont) 
    {
        // Buscar usuario en el array
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nom) && 
                usuario.getContrasena().equals(cont)) {
                
                // Usuario encontrado - guardar en sesión
                session.setAttribute("usuario", usuario);
                return "redirect:/";  //Redirigir al get mapping de arrriba que llevará a la pagina de login
            }
        }
        
        // Si no encuentra usuario, mostrar error
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    // Cerrar sesión
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        // Eliminar usuario de la sesión
        session.removeAttribute("usuario");
        return "redirect:/login";  // Redirigir al login
    }
}