package com.example.securityavanzado;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //para que no tome configuraiones por defecto y se las podamos dar nosotros
public class SecurityConfig {

    // Servicio que carga usuarios desde la base de datos
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepo) {
        return username -> {
            // Busca usuario en la base de datos
            var usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
            
            // Convierte roles a formato que Spring Security entiende
            String[] roles = usuario.getRoles().stream()
                .map(rol -> rol.getNombre().replace("ROLE_", "")) // Quita "ROLE_" del nombre
                .toArray(String[]::new);
            
            // Construye el usuario para Spring Security
            return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(roles)
                .disabled(!usuario.isActivo())
                .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/publica").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/usuario").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")//le decimos cual es la pagina del login
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}