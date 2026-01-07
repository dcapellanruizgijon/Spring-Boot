package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorios.UsuarioRepository;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.Usuario;

@Service
public class UsuarioServicioImplementado implements UsuarioServicio{

    @Autowired
    private UsuarioRepository repo;

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
    public Usuario guardarTrabajador(Usuario u) {
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
    public Usuario traerUsuario(Integer idUsuario) {
        return repo.findById(idUsuario).get();
    }
    
} 

    
