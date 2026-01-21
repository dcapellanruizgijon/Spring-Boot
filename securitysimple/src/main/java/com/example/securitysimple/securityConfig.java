package com.example.securitysimple;


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
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configuración de autorización de rutas (qué puede ver cada uno)
            .authorizeHttpRequests(autorizado -> autorizado
                //los request indican las rutas y lo siguiente los permisos
                .requestMatchers("/").permitAll() //(Regla 1) Permite acceso público a la ruta "/"
                .anyRequest().authenticated() //(Regla 2) Todas las demás rutas requieren autenticación
            )
            
            // Configuración de autenticación (Como se logean)
            .formLogin(formulario -> formulario //
                .defaultSuccessUrl("/home", true) // Redirige a /home después de login exitoso (configura lo que pasa despues de un login exitoso)
                .permitAll() // Permite acceso público a la página de login
            )
            
            // Configuración de logout (cómo se desloguean)
            .logout(deslogueo -> deslogueo    //Configura login por formulario
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/") // Redirige a "/" después del logout(cerrar sesion) exitoso
                .invalidateHttpSession(true) // Invalida la sesión
                .deleteCookies("JSESSIONID") // Elimina cookies de sesión
                .permitAll() // Permite acceso público al endpoint de logout
            );
        
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();// Es una interfaz que Spring Security usa para cargar información de usuarios (es lo que devolvemos al final, despues de añadir este usuario)

        //creamos el usuario
        UserDetails user1 = User.builder().username("admin")
        .password(this.passwordEncoder().encode("admin"))
        .authorities("write")//permisos
        .build();//termina la construcción

        //otro usuario
        UserDetails user2 = User.builder().username("normal")
        .password(this.passwordEncoder().encode("normal"))
        .authorities("write")
        .build();

        //Añade los usuarios al gestor de usuarios
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;//devuelve el gestor con el usuario ya registrado
    }
    @Bean//crea otro objeto gestionado por spring
    public PasswordEncoder passwordEncoder() {//passwordEncoder== Interfaz para encriptar/verificar contraseñas
        return new BCryptPasswordEncoder();//implementación que usa un algoritmo para codificar
    }
}