package com.example.securityavanzado;

import com.example.securityavanzado.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

//LE DAMOS DATOS A LA BBDD H2
@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner cargarDatosIniciales(
            UsuarioRepository usuarioRepo,
            RolRepository rolRepo,
            PasswordEncoder encoder) {
        
        return args -> {
            // Crear roles si no existen
            if (rolRepo.count() == 0) {
                Rol rolUser = new Rol("ROLE_USER");
                Rol rolAdmin = new Rol("ROLE_ADMIN");
                rolRepo.save(rolUser);
                rolRepo.save(rolAdmin);
                System.out.println("Roles creados: USER y ADMIN");
            }
            
            // Crear usuario normal si no existe
            if (!usuarioRepo.findByUsername("user").isPresent()) {
                Usuario user = new Usuario("user", encoder.encode("123"));
                user.addRol(rolRepo.findByNombre("ROLE_USER").get());
                usuarioRepo.save(user);
                System.out.println("Usuario 'user' creado con contraseña '123'");
            }
            
            // Crear administrador si no existe
            if (!usuarioRepo.findByUsername("admin").isPresent()) {
                Usuario admin = new Usuario("admin", encoder.encode("456"));
                admin.addRol(rolRepo.findByNombre("ROLE_USER").get());
                admin.addRol(rolRepo.findByNombre("ROLE_ADMIN").get());
                usuarioRepo.save(admin);
                System.out.println("Usuario 'admin' creado con contraseña '456' (tiene roles USER y ADMIN)");
            }
        };
    }
}
