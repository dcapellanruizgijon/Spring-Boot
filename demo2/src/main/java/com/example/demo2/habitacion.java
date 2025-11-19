package com.example.demo2;

public class habitacion {
    private Integer id;
    private String numero;
    private TipoHabitacion tipo;
    private Double precioNoche;
    private EstadoHabitacion estado;
    
    // Enums para tipo y estado
    public enum TipoHabitacion { INDIVIDUAL, DOBLE, SUITE }
    public enum EstadoHabitacion { DISPONIBLE, OCUPADA, MANTENIMIENTO }
    
    // Constructores, getters y setters
    // Validación de precio entre 40-300
}