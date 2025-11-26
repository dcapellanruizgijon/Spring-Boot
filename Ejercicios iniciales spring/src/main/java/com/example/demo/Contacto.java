package com.example.demo;

public class Contacto {
	
	private String nombre;
	private String apellido;
	private String correo;
	private Sexo Sexo;
	
	public Contacto(String nombre, String apellido, String correo, com.example.demo.Sexo sexo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		Sexo = sexo;
	}

	public Contacto() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Sexo getSexo() {
		return Sexo;
	}

	public void setSexo(Sexo sexo) {
		Sexo = sexo;
	}
	
}
