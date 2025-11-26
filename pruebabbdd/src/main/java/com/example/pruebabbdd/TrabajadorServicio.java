package com.example.pruebabbdd;

import java.util.List;

public interface TrabajadorServicio {

    public List<Trabajador> listarTrabajador();

    public Trabajador guardarTrabajador(Trabajador trabajador);

    public Trabajador obtenerTrabajador(Integer id);

    public Trabajador actualizarTrabajador(Trabajador trabajador);

    public void borrarTrabajador(Integer id);
}
