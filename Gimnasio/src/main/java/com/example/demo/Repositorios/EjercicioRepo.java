package com.example.demo.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.clases.Ejercicio;

public interface EjercicioRepo extends JpaRepository<Ejercicio, Integer>{
    List<Ejercicio> findByRId(Integer rutinaId);//este nombre es MUY IMPORTANTE para que JPA sepa que es lo que buscar:   findByRutina   JPA BUSCA EN LA CLASE EJERCICIO EL ATRIBUTO QUE SE LLAMA r y busca Su id (el id de la rutina)
}
