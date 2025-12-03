package com.example.pruebabbdd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//en esta clase implementamos los metodos de la interfaz TrabajadorServicio

@Service
public class TrabajadorServicioImplementar implements TrabajadorServicio {

    @Autowired//sirve para inyectar alguna dependencia
    private TrabajadorRepository repositorio;//"importamos" la interfaz TrabajadorRepository (que tiene un monton de métodos)


//Y aqui implementamos algunos de estos metodos para poder usarlos
    
    @Override
    public List<Trabajador> listarTrabajador() {

        return repositorio.findAll();
    }

    @Override
    public Trabajador guardarTrabajador(Trabajador trabajador) {
        return repositorio.save(trabajador);
    }

    @Override
    public Trabajador obtenerTrabajador(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Trabajador actualizarTrabajador(Trabajador trabajador) {
        return repositorio.save(trabajador);
    }

    @Override
    public void borrarTrabajador(Integer id) {
        repositorio.deleteById(id);
    }

}