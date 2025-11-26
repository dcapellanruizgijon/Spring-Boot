package com.ejemplo.model;

import java.time.LocalDateTime;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private int edad;
    private boolean activo;
    private LocalDateTime fechaRegistro;
    
    // Constructores
    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }
    
    public Usuario(Long id, String nombre, String email, int edad) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + "', email='" + email + "'}";
    }
}