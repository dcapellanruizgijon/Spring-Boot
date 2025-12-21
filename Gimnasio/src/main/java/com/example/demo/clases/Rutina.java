package com.example.demo.clases;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rutinas")
public class Rutina {
    //el id no lo incluimos en el constructor ya que se genera y guarda solo en la bbdd
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//debe ser integer (para que pueda ser null) en el resto de columnas usamos int a menos que en algun momento tenga que ser null

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")//se creará una columna con este nombre en la tabla Rutinas
    private Usuario u;

    private ArrayList<Ejercicio> ejercicios;
    
    public Rutina(String nombre, Usuario u) {
        this.nombre = nombre;
        this.u=u;
        this.ejercicios = new ArrayList<>();
    }
    public Rutina(){

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getU() {
        return u;
    }
    public void setU(Usuario u) {
        this.u = u;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
    
    // Método para agregar ejercicio al ArrayList
    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }
}