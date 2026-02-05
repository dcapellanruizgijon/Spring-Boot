package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repositorios.UsuarioRepository;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;

@Service
public class UsuarioServicioImplementado implements UsuarioServicio{

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder; /*para encriptar la contraseña */

    //devuelve una lista de todos los usuarios
    @Override
    public List<Usuario> listarUsuarios() {//hay que hacerlo con list
        return repo.findAll();
    }

    @Override//findById devuelve un type Optional que puede ser un usuario o ser null.  .get() lo que hace es extraer ese valor
    public Usuario obtenerUsuario(Integer id) {
        return repo.findById(id).get();//no se por que da ese warn
    }

    @Override  
                 /*el nombre seria guardarUsuario()  */
    public void guardarTrabajador(Usuario usuario) {
        System.out.println("=== ENTRANDO A guardarTrabajador ===");
        System.out.println("📝 Usuario recibido:");
        System.out.println("   Nombre: " + usuario.getNombre());
        System.out.println("   Contraseña original: " + usuario.getContrasena());
        
        // 1. Encriptar
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        System.out.println("🔐 Contraseña encriptada:");
        System.out.println("   Hash: " + contrasenaEncriptada);
        System.out.println("   Es BCrypt?: " + contrasenaEncriptada.startsWith("$2a$"));
        
        usuario.setContrasena(contrasenaEncriptada);
        
        // 2. Guardar
        System.out.println("💿 Guardando en repository...");
        Usuario guardado = repo.save(usuario);
        
        System.out.println("✅ Usuario guardado:");
        System.out.println("   ID asignado: " + guardado.getId());
        System.out.println("   Nombre: " + guardado.getNombre());
        System.out.println("=== SALIENDO DE guardarTrabajador ===");
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) {
        return repo.save(u);
    }

    @Override
    public void borrarUsuario(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario traerUsuario(Integer idUsuario) {
        return repo.findById(idUsuario).get();
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    
} 

    
