package com.example.demo.Servicios;

import java.util.List;

import com.example.demo.clases.Rutina;

public interface RutinaServicio {
    public List<Rutina> listarTodasLasRutinas();

    public Rutina guardarRutina(Rutina r);

    public void borrarRutina(Integer id);
    

}
