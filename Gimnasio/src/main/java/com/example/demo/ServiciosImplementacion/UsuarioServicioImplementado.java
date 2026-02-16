package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorios.UsuarioRepository;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class UsuarioServicioImplementado implements UsuarioServicio {

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder; /* para encriptar la contraseña */

    // devuelve una lista de todos los usuarios
    @Override
    public List<Usuario> listarUsuarios() {// hay que hacerlo con list
        return repo.findAll();
    }

    // @Override//findById devuelve un type Optional que puede ser un usuario o ser
    // null. .get() lo que hace es extraer ese valor
    // @Cacheable(value = "usuarios", key = "#id")//Para que una vez rescatado un
    // usuario no tenga que volver a acceder a la base de datos
    // public Usuario obtenerUsuario(Integer id) {
    // return repo.findById(id).get();//no se por que da ese warn
    // }

    @Override /* el nombre seria guardarUsuario() */
    public Usuario guardarTrabajador(Usuario u) {
        // Encriptar contraseña antes de guardar
        String contrasenaEncriptada = passwordEncoder.encode(u.getContrasena());
        u.setContrasena(contrasenaEncriptada);
        return repo.save(u);
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
    @Cacheable(value = "usuarios", key = "#idUsuario")
    public Usuario traerUsuario(Integer idUsuario) {
        System.out.println(" CONSULTANDO BBDD para usuario ID: " + idUsuario);
        return repo.findById(idUsuario).get();
    }

    @Override
    @Cacheable(value = "usuariosBasicos", key = "#nombre")
    public Usuario buscarPorNombre(String nombre) {
        System.out.println(" CONSULTANDO BBDD (básico) para: " + nombre);
        return repo.findByNombre(nombre);
    }

    @Override
    @Cacheable(value = "usuariosCompletos", key = "#id")
    public Usuario traerUsuarioConTodo(Integer id) {
        System.out.println(" CARGANDO usuario COMPLETO: " + id);
        Usuario usuario = repo.findById(id).get();
        usuario.getRutinas().size(); // Forzar carga
        return usuario;
    }

    @Override
    public Usuario traerUsuarioSinCache(Integer idUsuario) {
        System.out.println(" CONSULTANDO BBDD (SIN CACHÉ) para usuario ID: " + idUsuario);
        return repo.findById(idUsuario).get();
    }

}
