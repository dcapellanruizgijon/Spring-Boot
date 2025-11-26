package ejercicios3;

import repaso.repasoCompareTo;

public class Movil implements Comparable<Movil> {
	
	private String marca;
	private String modelo;
	private int precio;

	public Movil(String marca, String modelo, int precio) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}

	public Movil() {
		super();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Móvil: " + this.marca + " " + this.modelo + "\tPrecio: " + this.precio + "€";
	}
	
	@Override
	public int compareTo(Movil o) {
		// de mayor a menor
		return o.getPrecio() - getPrecio();
		// de menor a mayor
		// return getPrecio() - o.getPrecio();
	}
}
