package com.example.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
//aqui dentro se nos permite crear mas de un usuario
@Configuration
public class SecurityConfig {
    //@Bean le dice a spring que esto es un OBJETO que queremos que gestione
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