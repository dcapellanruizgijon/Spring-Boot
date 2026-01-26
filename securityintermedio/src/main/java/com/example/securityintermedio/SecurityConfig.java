package com.example.securityintermedio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configuración de autorización de rutas (consolidada)
            .authorizeHttpRequests(autorizado -> autorizado
                // Rutas públicas
                .requestMatchers("/", "/login").permitAll()
                
                // Rutas protegidas por roles
                .requestMatchers("/home").authenticated() // Cualquier usuario autenticado
                .requestMatchers("/root").hasRole("ADMIN") // Solo con rol ADMIN
                
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            
            // Configuración de autenticación (Cómo se logean)
            .formLogin(formulario -> formulario
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            
            // Configuración de logout
            .logout(deslogueo -> deslogueo
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        // Usuario ADMIN con múltiples roles
        UserDetails user1 = User.builder()
            .username("admin")
            .password(this.passwordEncoder().encode("admin"))
            .authorities("write")
            .roles("ADMIN", "USER") // Tiene roles ROLE_ADMIN y ROLE_USER
            .build();

        // Usuario normal USER
        UserDetails user2 = User.builder()
            .username("normal")
            .password(this.passwordEncoder().encode("normal"))
            .authorities("write")
            .roles("USER") // Tiene rol ROLE_USER
            .build();

        // Usuario con rol GUEST (corregido username único)
        UserDetails user3 = User.builder()
            .username("guest")
            .password(this.passwordEncoder().encode("guest"))
            .authorities("read") // Solo permiso de lectura
            .roles("GUEST") // Rol GUEST
            .build();

        // Añade los usuarios al gestor
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);
        userDetailsService.createUser(user3);

        return userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}