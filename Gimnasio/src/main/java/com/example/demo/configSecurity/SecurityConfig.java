package com.example.demo.configSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())   /*MUY IMPORTANTE PARA QUE NO FALLE EL POST DEL REGISTRO */
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/registro",
                                "/swagger-ui/**",      // AÑADE ESTO
                               "/v3/api-docs/**",     // AÑADE ESTO
                               "/swagger-ui.html")
                .permitAll()// Páginas públicas
                
                .anyRequest().authenticated()   // Resto protegido
            )
            .formLogin(form -> form
                .loginPage("/login")    // Tu página de login personalizada
                .loginProcessingUrl("/login")
                .successHandler(successHandler) 
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/cerrarSesion")// Mantiene nuestro endpoint
                .logoutSuccessUrl("/login?logout=true")// Redirige al login tras logout
                .deleteCookies("JSESSIONID", "ultimaRutina")
                .invalidateHttpSession(true)
                .permitAll()
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }

}
