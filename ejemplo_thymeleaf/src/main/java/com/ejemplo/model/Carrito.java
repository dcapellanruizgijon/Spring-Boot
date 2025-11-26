package com.ejemplo.model;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Long, Integer> items; // productoId -> cantidad
    private double total;
    
    public Carrito() {
        this.items = new HashMap<>();
        this.total = 0.0;
    }
    
    public void agregarProducto(Producto producto, int cantidad) {
        items.put(producto.getId(), items.getOrDefault(producto.getId(), 0) + cantidad);
        calcularTotal();
    }
    
    public void eliminarProducto(Long productoId) {
        items.remove(productoId);
        calcularTotal();
    }
    
    public void actualizarCantidad(Long productoId, int cantidad) {
        if (cantidad <= 0) {
            eliminarProducto(productoId);
        } else {
            items.put(productoId, cantidad);
        }
        calcularTotal();
    }
    
    private void calcularTotal() {
        // En una aplicación real, buscarías los productos en una base de datos
        this.total = items.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * 10.0) // Precio simulado
                .sum();
    }
    
    public void limpiar() {
        items.clear();
        total = 0.0;
    }
    
    // Getters
    public Map<Long, Integer> getItems() { return items; }
    public double getTotal() { return total; }
    public int getTotalItems() { 
        return items.values().stream().mapToInt(Integer::intValue).sum(); 
    }
}