package com.example.demo.clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements OperacionesSalud{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;
    @Column
    private String contrasena;
    @Column
    private int edad;
    @Column
    private double peso;
    @Column
    private double altura;
    @Column
    private String rol = "USER"; // NUEVO: USER por defecto, ADMIN para administradores
    
    @OneToMany(mappedBy = "u")
    private List<Rutina> rutinas;
    
    // Constructor existente
    public Usuario(String nombre, String contrasena, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.rutinas = new ArrayList<>();
        this.rol = "USER"; // Por defecto es USER
    }
    
    // Constructor con ID
    public Usuario(Integer id, String nombre, String contrasena, int edad, double peso, double altura) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.rutinas = new ArrayList<>();
        this.rol = "USER";
    }
    
    // Constructor vacío
    public Usuario() {
        this.rol = "USER";
    }
    
    // Getters y Setters existentes...
    public Integer getId() {
        return this.id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public double getAltura() {
        return altura;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }
    
    public List<Rutina> getRutinas() {
        return this.rutinas;
    }
    
    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
    
    // NUEVOS getter y setter para rol
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    // Método helper para verificar si es admin
    public boolean isAdmin() {
        return "ADMIN".equals(this.rol);
    }
    
    public void agregarRutina(Rutina rutina) {
        this.rutinas.add(rutina);
    }
    
    @Override
    public double calculaIndiceMasaCorporal() {
        System.out.println("Altura:" + this.altura);
        System.out.println("peso:" + this.peso);
        if (this.peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser un valor positivo");
        }

        if (this.altura <= 30) {
            throw new IllegalArgumentException("La altura parece ser incorrecta (muy pequeña)");
        }

        if (this.altura >= 300) {
            throw new IllegalArgumentException("La altura parece ser incorrecta (muy grande)");
        }

        double alturaEnMetros = this.altura / 100;
        return this.peso / (alturaEnMetros * alturaEnMetros);
    }
    
    @Override
    public String obtenerCategoriaIMC() {
        double imc = calculaIndiceMasaCorporal();
        if (imc < 18.5) return "Bajo peso";
        else if (imc < 25) return "Normal";
        else if (imc < 30) return "Sobrepeso";
        else return "Obesidad";
    }
}