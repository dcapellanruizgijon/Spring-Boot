package com.example.pruebabbdd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Marca esta interfaz como un componente de persistencia de Spring
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {  //JpaRepository se pone siempre.  
                                                                                    //El primer prametro es el objeto con el que trabajará el repositorio ==> Trabajador
}                                                                                   //El tipo de dato de la clave primaria (no puede ser un tipo primitivo (int)) ===> Integer
