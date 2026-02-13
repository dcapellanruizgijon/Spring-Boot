package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorios.RutinaRepository;
import com.example.demo.Servicios.RutinaServicio;
import com.example.demo.clases.Ejercicio;
import com.example.demo.clases.Rutina;
import com.example.demo.clases.Usuario;

@Service
public class RutinaServicioImplementacion implements RutinaServicio{

    @Autowired
    private RutinaRepository repoRu;
    
    @Override
    public List<Rutina> listarTodasLasRutinas() {
        return repoRu.findAll();
    }

    @Override
    public Rutina guardarRutina(Rutina r) {
        return repoRu.save(r);
    }

    @Override
    public void borrarRutina(Integer id) {
        repoRu.deleteById(id);
        
    }

    @Override
    public Rutina getRutinaById(Integer id){
        return repoRu.findById(id).get();
    }
    
    


}
