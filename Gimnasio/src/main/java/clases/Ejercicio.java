package clases;

public class Ejercicio {
    private String nombre;
    private boolean completado;
    
    public Ejercicio(String nombre) {
        this.nombre = nombre;
        this.completado = false;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}