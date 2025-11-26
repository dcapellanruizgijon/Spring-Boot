package com.example.pruebabbdd;

import java.util.List;

//En esta interfaz definimos que se puede hacer y como
public interface TrabajadorServicio {

    //podemos listar todos los trabajadores
    public List<Trabajador> listarTrabajador();

    public Trabajador guardarTrabajador(Trabajador trabajador);

    public Trabajador obtenerTrabajador(Integer id);

    public Trabajador actualizarTrabajador(Trabajador trabajador);

    public void borrarTrabajador(Integer id);
}
