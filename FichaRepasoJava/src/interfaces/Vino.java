package interfaces;

public class Vino implements EsLiquido, ConDescuento {

	private String marca;
	private String tipo;
	private double grados;
	private double precio;
	
	public Vino(String marca, String tipo, double grados, double precio) {
		super();
		this.marca = marca;
		this.tipo = tipo;
		this.grados = grados;
		this.precio = precio;
	}
	
	public Vino() {
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

	public double getGrados() {
		return grados;
	}

	public void setGrados(double grados) {
		this.grados = grados;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return this.marca + " " + this.tipo + " " + this.grados + " Calorías: " + (this.grados * 10) + "\t" + this.precio + "€";
	}

	@Override
	public void setDescuento(double des) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPrecioDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVolumen(int v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVolumen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTipoEnvase(String env) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTipoEnvase() {
		// TODO Auto-generated method stub
		return null;
	}
}
