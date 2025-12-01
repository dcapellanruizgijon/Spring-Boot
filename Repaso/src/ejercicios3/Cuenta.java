package ejercicios3;

public class Cuenta {
	private String titular;
	private double cantidad;

	public Cuenta(String titular, double cantidad) {
		super();
		this.titular = titular;
		this.cantidad = cantidad;
	}
	// Constructor vacio
	public Cuenta() {
		super();
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Cuenta [titular=" + titular + ", cantidad=" + cantidad + "]";
	}
	
	public void ingresar(double cantidad) {
		if (cantidad > 0) {
			this.cantidad += cantidad;
		}
	}
	
	public void retirar(double cantidad) {
		if (this.cantidad - cantidad < 0) {
			this.cantidad = 0;
		} else {
			this.cantidad -= cantidad;
		}
	}

}
