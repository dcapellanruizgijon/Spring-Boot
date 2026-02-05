package com.example.demo.Servicios;

import java.util.List;

import com.example.demo.clases.Rutina;
import com.example.demo.clases.Usuario;

public interface UsuarioServicio {
    public List<Usuario> listarUsuarios();

    public Usuario traerUsuario(Integer idUsuario);

    public Usuario guardarTrabajador(Usuario t);

    public Usuario obtenerUsuario(Integer id);

    public Usuario buscarPorNombre(String nombre);

    public Usuario actualizarUsuario(Usuario trabajador);

    public void borrarUsuario(Integer id);
}
