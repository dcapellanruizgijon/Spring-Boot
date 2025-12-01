package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//para poder usar el @NonNull(el atributo que esté debajo de un @NonNull no lo incluirá en el constructor)
@NoArgsConstructor//constructor sin argumentos (En springboot es recomendable crearlo siempre)
@AllArgsConstructor//constructor con todos los argumentos
public class Persona {
    @NonNull
    private String nombre;
    private int edad;
    
    public Persona(int edad){
        this.edad=edad;
    }
}


