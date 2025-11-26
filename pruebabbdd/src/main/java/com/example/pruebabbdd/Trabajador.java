package com.example.pruebabbdd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//sin @Table y sin @Column tomaría por defecto los nombres de la clase y los atributos 



/* PARA QUE ESTO FUNCIONE TIENE QUE ESTAR YA CREADA LA TABLA "trabajadores" */

@Entity//le dice a hibernate/JPA que esta clase es una tabla de una bbdd
@Table(name = "trabajadores")//especifica el nombre de la tabla en la bbdd
public class Trabajador {
    
    @Id//marca este atributo como clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//define como se generan los valores para la clave primaria (strategy IDENTITY ==> auto incrementacion de la bbbdd (si se añade un elemeto se crea debajo con el numero siguiente al ultimo registro (si se elimina un registro los numeros se mantienen como estan y no se reorganizan)))
    private Integer id;

    @Column(name = "nombre")//nombre de la columna de la base de datos
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email", unique = true)//unique ==> para que no pueda repetirse
    private String email;

    public Trabajador() {
    }

    public Trabajador(Integer id, String nombre, String apellidos, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public Trabajador(String nombre, String apellidos, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Trabajador [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
                + ", email=" + email + "]";
    }

    
}
