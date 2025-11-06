package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rutina {

	 private String nombre;
	 private String descripción;
	 private LocalDate fechaCreacion;//localDate para fecha
	 private ArrayList<Ejercicio> listaEjercicios; 
	    
	 public Rutina(String nombre, String descripcion) {
		 this.nombre = nombre;
	     this.descripción = descripcion;
	     this.fechaCreacion = LocalDate.now();
	     this.listaEjercicios = new ArrayList<>();
	 }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getDescripción() {
	        return descripción;
	    }

	    public void setDescripción(String descripción) {
	        this.descripción = descripción;
	    }

	    public LocalDate getFechaCreacion() {
	        return fechaCreacion;
	    }

	    public void setFechaCreacion(LocalDate fechaCreacion) {
	        this.fechaCreacion = fechaCreacion;
	    }
	    
	    public ArrayList<Ejercicio> getListaEjercicios() {
	        return listaEjercicios;
	    }

	    public void setListaEjercicios(ArrayList<Ejercicio> listaEjercicios) {
	        this.listaEjercicios = listaEjercicios;
	    }
	    
	    public void agregarEjercicio(Ejercicio ejercicio) {
	        this.listaEjercicios.add(ejercicio);
	    }
	    
	    public void eliminarEjercicio(int indice) {
	        if(indice > 0 && indice < this.listaEjercicios.size()) {  //en arrayList se usa .size() en lugar de .length()   
	            this.listaEjercicios.remove(indice);
	        }
	    }
	        
	    public int getTotalEjercicios() {
	        return this.listaEjercicios.size();
	    }
	        
	    //para saber si un ejercicio está terminado
	    public int getEjerciciosCompletados() {
	        int completados = 0;
	        
	        for (Ejercicio ejercicio : this.listaEjercicios) {
	            if (ejercicio.isCompletado()) {//isCompletado devuelve true o false, es un atributo de la clase ejercicio
	                completados++;
	            }
	        }
	        return completados;
	    }
	
}
