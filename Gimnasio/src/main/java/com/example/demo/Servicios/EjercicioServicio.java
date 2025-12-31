package com.example.demo.Servicios;

import java.util.List;

import com.example.demo.clases.Ejercicio;

public interface EjercicioServicio {
    public List<Ejercicio> listarTodosLosEjercicio();

    public Ejercicio traerEjercicio(Integer idEjercicio);

    public void guardarEjercicio(Ejercicio ej);

    public void eliminarEjercicicio(Ejercicio e);

    public List<Ejercicio> listarEjsDeUnaRutina(Integer id);//se le pasa el id de la rutina
    
}
