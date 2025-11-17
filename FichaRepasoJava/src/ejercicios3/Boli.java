package ejercicios3;

public class Boli {
	private String marca;
	private String tipo;
	private double precio;
	
	public Boli(String marca, String tipo, double precio) {
		super();
		this.marca = marca;
		this.tipo = tipo;
		this.precio = precio;
	}
	
	public Boli() {
		super();
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
