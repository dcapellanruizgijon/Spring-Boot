package com.example.demo.configSecurity;

import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      Authentication authentication) throws IOException, ServletException {
        
        String username = authentication.getName();
        
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioServicio.buscarPorNombre(username);
        
        if (usuario != null) {
            // Inicializar las relaciones Hibernate (igual que hacías antes)
            Hibernate.initialize(usuario.getRutinas());
            if (usuario.getRutinas() != null) {
                for (com.example.demo.clases.Rutina rutina : usuario.getRutinas()) {
                    Hibernate.initialize(rutina.getEjercicios());
                }
            }
            
            // Guardar el usuario completo en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
        }
        
        // Redirigir a la página principal
        response.sendRedirect("/");
    }
}