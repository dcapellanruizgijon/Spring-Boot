package com.example.demo.clases;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;
    
    @Column
    private boolean completado;

    @ManyToOne
    @JoinColumn(name = "rutina_id")//nombre que se le dará a la columna
    @JsonIgnore //para evitar el bucle infinito con swager
    private Rutina r;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;
    
    public Ejercicio(String nombre, Rutina r) {
        this.nombre = nombre;
        this.completado = false;
        this.r=r;
    }
    public Ejercicio(){

    }
    

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado() {
        if (this.completado==true) {
            this.completado=false;
        }
        else if(this.completado==false){
            this.completado=true;
        }
    }

    public Rutina getR() {
        return r;
    }
    public String getImagenUrl() {
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    
    
}