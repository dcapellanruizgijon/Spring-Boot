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
@EnableWebSecurity  //esto activa la configuración personalizada (desactiva la automática)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //desactiva la protección contra algunos ataques
            .csrf(csrf -> csrf.disable())

            // Configura qué URLs requieren qué permisos
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/publica").permitAll()  // Acceso libre
                .requestMatchers("/admin").hasRole("ADMIN")    // Solo admin
                .requestMatchers("/usuario").hasAnyRole("USER", "ADMIN")  // User o admin (hashAnyRole controla el acceso por roles)
                .anyRequest().authenticated()  // El resto requiere login
            )

            .formLogin(form -> form
                .loginPage("/login")//DESACTIVA LA PAGINA DE LOGIN AUTOMATICA (le damos nosotros cual es la página de login)
                .defaultSuccessUrl("/")//rediricge después de login exitoso
                .permitAll()//permite acceso a login sin autenticacion
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/")// Va aquí tras logout
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("123"))
            .roles("USER")//le damos el rol de usuario
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("456"))
            .roles("ADMIN")//le damos el rol de administrador
            .build();


            //GUARDA LOS USUARIOS EN MEMORIA
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//encripta la contraseña con BCrypt de forma segura
    }
}