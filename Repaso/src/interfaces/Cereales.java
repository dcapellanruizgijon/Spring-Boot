package interfaces;

import java.time.LocalDate;

public class Cereales implements EsAlimento {

	private String marca;
	private double precio;
	private TipoCereal cereal;

	public Cereales(String marca, double precio, TipoCereal cereal) {
		super();
		this.marca = marca;
		this.precio = precio;
		this.cereal = cereal;
	}

	public Cereales() {
		super();
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public TipoCereal getCereal() {
		return cereal;
	}

	public void setCereal(TipoCereal cereal) {
		this.cereal = cereal;
	}

	public String toString() {
		int calorias = 0;
		switch (this.cereal) {
		case Avena:
			calorias = 5;
			break;
		case Maíz:
			calorias = 8;
			break;
		case Trigo:
			calorias = 12;
			break;
		case Otro:
			calorias = 15;
			break;
		}
		return this.marca + " " + this.cereal + " Calorías: " + calorias + "\t" + this.precio + "€";
	}

	@Override
	public void setCaducidad(LocalDate fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalDate getCaducidad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return 0;
	}
}
