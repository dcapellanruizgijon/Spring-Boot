package clases;

import java.util.ArrayList;

public class Usuario {
	
	private String nombre;
    private String contrasena;
    private int edad;
    private double peso;
    private double altura;
    private ArrayList<Rutina> rutinas;
    
    public Usuario(String nombre, String contrasena, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.rutinas = new ArrayList<>();
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

	public ArrayList<Rutina> getRutinas() {
		return rutinas;
	}

	
	public void setRutinas(ArrayList<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	
	//para calcular el indice de masa corporal
	public double calculaIndiceMasaCorporal() {
        return peso / ((altura/100) * (altura/100));
    }
	
}
