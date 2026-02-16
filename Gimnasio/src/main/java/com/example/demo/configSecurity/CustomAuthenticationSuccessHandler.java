package com.example.demo.configSecurity;

import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Ejercicio;
import com.example.demo.clases.Rutina;
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

        // Buscar el usuario en la base de datosy le mete ya las rutinas antes de
        // guardarlo en las sesion
        Usuario usuarioBasico = usuarioServicio.buscarPorNombre(username);

        if (usuarioBasico != null) {
            // Obtener el usuario completo con todas sus relaciones
            Usuario usuarioCompleto = usuarioServicio.traerUsuarioConTodo(usuarioBasico.getId());

            Usuario usuarioLimpio = desproxificarUsuario(usuarioCompleto);

            // Guardar el usuario completo en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioLimpio);
        }

        // Redirigir a la página principal
        response.sendRedirect("/");
    }

    private Usuario desproxificarUsuario(Usuario usuario) {
        org.hibernate.Hibernate.unproxy(usuario);

        if (usuario.getRutinas() != null) {
            for (Rutina rutina : usuario.getRutinas()) {
                org.hibernate.Hibernate.unproxy(rutina);
                if (rutina.getEjercicios() != null) {
                    for (Ejercicio ejercicio : rutina.getEjercicios()) {
                        org.hibernate.Hibernate.unproxy(ejercicio);
                    }
                }
            }
        }
        return usuario;
    }

}