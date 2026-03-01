package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorios.UsuarioRepository;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Rutina;
import com.example.demo.clases.Usuario;

import jakarta.transaction.Transactional;

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
    // return repo.findById(id).get();
    // }

    @Override
    //limpia la caché para que se pueda encontrar a un usuario reciencreado cuando se hace login
    @CacheEvict(value = { "usuariosBasicos", "usuariosCompletos", "usuarios",
            "usuariosCompletosPorNombre" }, key = "#u.nombre", allEntries = true) // Limpia toda la caché relacionada
    public Usuario guardarTrabajador(Usuario u) {
        // Encriptar contraseña antes de guardar
        String contrasenaEncriptada = passwordEncoder.encode(u.getContrasena());
        u.setContrasena(contrasenaEncriptada);
        System.out.println("Usuario guardado y caché limpiada para: " + u.getNombre());
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
    @Cacheable(value = "usuarios", key = "#idUsuario")//ALMACENA EL RESULTADO DE UN MÉTODO EN CACHÉ PARA QUE NO SE EJECUTE REPETIDAMENTE
    public Usuario traerUsuario(Integer idUsuario) {
        System.out.println(" CONSULTANDO BBDD para usuario ID: " + idUsuario);
        return repo.findById(idUsuario).get();
    }

    // devuelve y gurada en la caché el usuario por nombre
    @Override
    @Cacheable(value = "usuariosBasicos", key = "#nombre")//ALMACENA EL RESULTADO DE UN MÉTODO EN CACHÉ PARA QUE NO SE EJECUTE REPETIDAMENTE
    public Usuario buscarPorNombre(String nombre) {
        System.out.println(" CONSULTANDO BBDD (básico) para: " + nombre);
        return repo.findByNombre(nombre);
    }

    /* devuelve el ususario por id */
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

    /* devuelve el usuario con rutinas y ejercicios */
    @Override
    @Cacheable(value = "usuariosCompletosPorNombre", key = "#nombre")//ALMACENA EL RESULTADO DE UN MÉTODO EN CACHÉ PARA QUE NO SE EJECUTE REPETIDAMENTE
    @Transactional
    public Usuario buscarUsuarioCompletoPorNombre(String nombre) {
        System.out.println("🔴 CARGANDO usuario COMPLETO desde BBDD (por nombre): " + nombre);

        // Primero busca por nombre
        Usuario usuario = repo.findByNombre(nombre);

        if (usuario != null) {
            // carga de todas las relaciones

            usuario.getRutinas().size(); // Carga las rutinas

            // para cargar los ejercicios de cada rutina
            for (Rutina rutina : usuario.getRutinas()) {
                rutina.getEjercicios().size(); // Carga los ejercicios
            }

        }

        return usuario;
    }

}
