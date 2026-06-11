package com.example.demo.configSecurity;

import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioServicio.buscarPorNombre(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        System.out.println("✅ Usuario encontrado para login: " + usuario.getNombre());
        System.out.println("   ID: " + usuario.getId());
        System.out.println("   Rol: " + usuario.getRol());

        // Crear authorities basadas en el rol del usuario
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
        
        return User.withUsername(usuario.getNombre())
                   .password(usuario.getContrasena())
                   .authorities(Collections.singletonList(authority))
                   .build();
    }
}