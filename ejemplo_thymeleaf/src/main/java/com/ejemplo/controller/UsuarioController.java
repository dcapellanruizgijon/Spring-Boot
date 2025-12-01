package com.ejemplo.controller;

import com.ejemplo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    // Simulamos una "base de datos" en memoria
    private List<Usuario> usuarios = new ArrayList<>();
    private Long nextId = 1L;
    
    public UsuarioController() {
        // Datos de ejemplo
        usuarios.add(new Usuario(nextId++, "Ana García", "ana@email.com", 25));
        usuarios.add(new Usuario(nextId++, "Carlos López", "carlos@email.com", 30));
        usuarios.add(new Usuario(nextId++, "María Ruiz", "maria@email.com", 28));
    }

    // =========================================================================
    // @GetMapping - EJEMPLOS
    // =========================================================================
    
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Lista de Usuarios");
        return "usuarios/lista";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarios/detalle";
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "error";
        }
    }
    
    @GetMapping("/buscar")
    public String buscarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            Model model) {
        
        List<Usuario> resultados = usuarios;
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            resultados = resultados.stream()
                    .filter(u -> u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .toList();
        }
        
        if (email != null && !email.trim().isEmpty()) {
            resultados = resultados.stream()
                    .filter(u -> u.getEmail().toLowerCase().contains(email.toLowerCase()))
                    .toList();
        }
        
        model.addAttribute("usuarios", resultados);
        model.addAttribute("nombreBuscado", nombre);
        model.addAttribute("emailBuscado", email);
        return "usuarios/lista";
    }

    // =========================================================================
    // @PostMapping - EJEMPLOS
    // =========================================================================
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("titulo", "Nuevo Usuario");
        return "usuarios/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(
            @ModelAttribute Usuario usuario,
            RedirectAttributes redirectAttributes) {
        
        // Validaciones básicas
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El nombre es obligatorio");
            return "redirect:/usuarios/nuevo";
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El email es obligatorio");
            return "redirect:/usuarios/nuevo";
        }
        
        // Guardar usuario
        if (usuario.getId() == null) {
            // Nuevo usuario
            usuario.setId(nextId++);
            usuarios.add(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
        } else {
            // Editar usuario existente
            usuarios.removeIf(u -> u.getId().equals(usuario.getId()));
            usuarios.add(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        }
        
        return "redirect:/usuarios/lista";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("titulo", "Editar Usuario");
            return "usuarios/formulario";
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "error";
        }
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        
        boolean eliminado = usuarios.removeIf(u -> u.getId().equals(id));
        
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario");
        }
        
        return "redirect:/usuarios/lista";
    }
    
    @PostMapping("/toggle-activo/{id}")
    public String toggleActivo(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        
        usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .ifPresent(usuario -> {
                    usuario.setActivo(!usuario.isActivo());
                    String estado = usuario.isActivo() ? "activado" : "desactivado";
                    redirectAttributes.addFlashAttribute("mensaje", 
                        "Usuario " + estado + " exitosamente");
                });
        
        return "redirect:/usuarios/lista";
    }
}