package com.example.demo.configSecurity;

import com.example.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Busca usuario en tu BD
        com.example.demo.clases.Usuario usuario = usuarioServicio.buscarPorNombre(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Crea UserDetails con los datos del usuario
        return User.withUsername(usuario.getNombre())
                   .password(usuario.getContrasena())  // Ya encriptada
                   .roles("USER")
                   .build();
    }
}