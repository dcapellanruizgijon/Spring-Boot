package com.example.practicando.ej3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//crea los getter para todos los atributos independientemente de los constructores
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private String titulo;
    private String autor;
    private int precio;
    private int isbn; 

    public boolean precioValido(int pre){
        if(pre>4 && pre<20){
            return true;
        }else{
            return false;
        }
    }
}
