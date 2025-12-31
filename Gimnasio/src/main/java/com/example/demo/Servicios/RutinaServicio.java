package com.example.demo.Servicios;

import java.util.List;

import com.example.demo.clases.Ejercicio;
import com.example.demo.clases.Rutina;
import com.example.demo.clases.Usuario;

public interface RutinaServicio {
    public List<Rutina> listarTodasLasRutinas();

    public Rutina guardarRutina(Rutina r);

    public void borrarRutina(Integer id);

    
    
    // public List<Rutina> rutinasDelUsuario(Usuario u);

}
