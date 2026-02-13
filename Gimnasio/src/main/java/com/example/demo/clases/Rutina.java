package com.example.demo.clases;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rutinas")
public class Rutina {
    //el id no lo incluimos en el constructor ya que se genera y guarda solo en la bbdd
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//debe ser integer (para que pueda ser null) en el resto de columnas usamos int a menos que en algun momento tenga que ser null

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")//se creará una columna con este nombre en la tabla Rutinas
    @JsonIgnore
    private Usuario u;

    @OneToMany(mappedBy = "r", cascade = CascadeType.REMOVE)//nombre del atributo de la clase Ejercicio.  Y EL CASCADE ES PARA QUE SI ELIMINAMOS UNA RUTINA SE ELIMINEN TAMBIÉN SUS EJERCICIOS
    private List<Ejercicio> ejercicios;//list en lugar de arrayList porque hibernnate no implementa ArrayList
    
    public Rutina(String nombre, Usuario u) {
        this.nombre = nombre;
        this.u=u;
        this.ejercicios = new ArrayList<>();
    }
    public Rutina(){

    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


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

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    //NO PUEDE TENER SETTER EL ARRAY DE EJERCICIO
    // public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
    //     this.ejercicios = ejercicios;
    // }
    
    // Método para agregar ejercicio al ArrayList
    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }
}