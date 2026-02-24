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

    @Autowired
    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();

        // ✅ AHORA USA EL MÉTODO CORRECTO que carga todo
        Usuario usuarioCompleto = usuarioServicio.buscarUsuarioCompletoPorNombre(username);

        if (usuarioCompleto != null) {
            // Desproxificar y guardar en sesión
            Usuario usuarioLimpio = desproxificarUsuario(usuarioCompleto);
            session.setAttribute("usuario", usuarioLimpio);
            System.out.println("✅ Usuario guardado en sesión: " + username);
        }

        response.sendRedirect("/");
    }

    private Usuario desproxificarUsuario(Usuario usuario) {
        // Desproxificar el usuario principal y ASIGNAR el resultado
        usuario = (Usuario) org.hibernate.Hibernate.unproxy(usuario);

        // Desproxificar rutinas
        if (usuario.getRutinas() != null) {
            for (int i = 0; i < usuario.getRutinas().size(); i++) {
                Rutina rutina = usuario.getRutinas().get(i);
                Rutina rutinaLimpia = (Rutina) org.hibernate.Hibernate.unproxy(rutina);
                usuario.getRutinas().set(i, rutinaLimpia);

                // Desproxificar ejercicios
                if (rutinaLimpia.getEjercicios() != null) {
                    for (int j = 0; j < rutinaLimpia.getEjercicios().size(); j++) {
                        Ejercicio ejercicio = rutinaLimpia.getEjercicios().get(j);
                        Ejercicio ejercicioLimpio = (Ejercicio) org.hibernate.Hibernate.unproxy(ejercicio);
                        rutinaLimpia.getEjercicios().set(j, ejercicioLimpio);
                    }
                }
            }
        }

        return usuario;
    }

}