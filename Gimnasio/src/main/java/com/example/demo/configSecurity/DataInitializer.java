package com.example.demo.configSecurity;

import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            Usuario adminExistente = usuarioServicio.buscarPorNombre("admin");
            
            if (adminExistente == null) {
                // NO encriptes aquí - pasa la contraseña en texto plano
                Usuario admin = new Usuario("admin", "admin123", 30, 70, 175);
                admin.setRol("ADMIN");
                usuarioServicio.guardarTrabajador(admin); // El servicio encriptará
                System.out.println("✅ Usuario ADMIN creado");
            } else {
                System.out.println("✅ El usuario admin ya existe con ID: " + adminExistente.getId());
                
                // Verificar si la contraseña está encriptada
                if (adminExistente.getContrasena() != null && !adminExistente.getContrasena().startsWith("$2a$")) {
                    System.out.println("⚠️ Actualizando contraseña del admin...");
                    adminExistente.setContrasena("admin123"); // Texto plano
                    usuarioServicio.guardarTrabajador(adminExistente); // El servicio encriptará
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error al inicializar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}