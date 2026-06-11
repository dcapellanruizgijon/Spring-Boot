package com.example.demo.controlador;

import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    

    // Verificar que el usuario es admin
    private boolean esAdmin(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        return usuario != null && usuario.isAdmin();
    }

    // Panel principal de admin
    @GetMapping("/panel")
    public String panelAdmin(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }
        
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioActual", session.getAttribute("usuario"));
        return "admin/panel";
    }

    // Mostrar formulario para crear usuario
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("usuario", new Usuario());
        return "admin/formulario-usuario";
    }

    // Crear nuevo usuario
    @PostMapping("/usuarios/crear")
    public String crearUsuario(@RequestParam String nombre,
                               @RequestParam String contrasena,
                               @RequestParam int edad,
                               @RequestParam double peso,
                               @RequestParam double altura,
                               @RequestParam(defaultValue = "USER") String rol,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }

        try {
            Usuario usuarioExistente = usuarioServicio.buscarPorNombre(nombre);
            if (usuarioExistente != null) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un usuario con ese nombre");
                return "redirect:/admin/usuarios/nuevo";
            }

            Usuario nuevoUsuario = new Usuario(nombre, contrasena, edad, peso, altura);
            nuevoUsuario.setRol(rol);
            usuarioServicio.guardarTrabajador(nuevoUsuario);
            redirectAttributes.addFlashAttribute("success", "Usuario creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear usuario: " + e.getMessage());
        }
        return "redirect:/admin/panel";
    }

    // Mostrar formulario de edición
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }
        
        Usuario usuario = usuarioServicio.traerUsuario(id);
        if (usuario == null) {
            return "redirect:/admin/panel";
        }
        
        model.addAttribute("usuario", usuario);
        return "admin/formulario-usuario";
    }

    // Actualizar usuario
    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Integer id,
                                    @RequestParam String nombre,
                                    @RequestParam int edad,
                                    @RequestParam double peso,
                                    @RequestParam double altura,
                                    @RequestParam String rol,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }

        try {
            Usuario usuario = usuarioServicio.traerUsuario(id);
            if (usuario == null) {
                redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
                return "redirect:/admin/panel";
            }

            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setPeso(peso);
            usuario.setAltura(altura);
            usuario.setRol(rol);
            
            usuarioServicio.guardarTrabajador(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar usuario: " + e.getMessage());
        }
        return "redirect:/admin/panel";
    }

    // Eliminar usuario (con confirmación)
    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, 
                                  HttpSession session, 
                                  RedirectAttributes redirectAttributes) {
        if (!esAdmin(session)) {
            return "redirect:/";
        }

        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");
        
        // No permitir eliminar a sí mismo
        if (usuarioActual.getId().equals(id)) {
            redirectAttributes.addFlashAttribute("error", "No puedes eliminar tu propio usuario");
            return "redirect:/admin/panel";
        }

        try {
            usuarioServicio.borrarUsuario(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar usuario: " + e.getMessage());
        }
        
        return "redirect:/admin/panel";
    }
}