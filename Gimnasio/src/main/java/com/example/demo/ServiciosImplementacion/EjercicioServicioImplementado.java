package com.example.demo.ServiciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositorios.EjercicioRepo;
import com.example.demo.Servicios.EjercicioServicio;
import com.example.demo.clases.Ejercicio;

@Service
public class EjercicioServicioImplementado implements EjercicioServicio{
    @Autowired
    private EjercicioRepo repo;

    @Override
    public List<Ejercicio> listarTodosLosEjercicio() {
        return repo.findAll();
    }

    @Override
    public void guardarEjercicio(Ejercicio ej) {
        repo.save(ej);
    }

    @Override
    public void eliminarEjercicicio(Ejercicio e) {
        repo.delete(e);
    }

    @Override
    public List<Ejercicio> listarEjsDeUnaRutina(Integer id) {
        return repo.findByRId(id);//explicado en EjercicioRepo
    }
    
    @Override
    public Ejercicio traerEjercicio(Integer idEjercicio) {
        return repo.findById(idEjercicio).get();
    }
}
