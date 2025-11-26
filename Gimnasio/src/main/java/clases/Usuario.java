package clases;

import java.util.ArrayList;

public class Usuario implements OperacionesSalud{
	
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

	//lo uso en el método de agregar ejercicio del controlador
	public ArrayList<Rutina> getRutinas() {
		return rutinas;
	}
	
	public void setRutinas(ArrayList<Rutina> rutinas) {
		this.rutinas = rutinas;
	}
	
	//para agregar una neva rutina
	public void agregarRutina(Rutina rutina) {
        this.rutinas.add(rutina);
    }
	

	
	//implementamos la interfaz de operaciones de salud:

	//para calcular el indice de masa corporal
	public double calculaIndiceMasaCorporal() {
	    // Validar que peso y altura sean valores positivos
	    if (peso <= 0) {
	        throw new IllegalArgumentException("El peso debe ser un valor positivo");
	    }
	    if (altura <= 0) {
	        throw new IllegalArgumentException("La altura debe ser un valor positivo");
	    }
	    
	    // Validar que la altura no sea demasiado pequeña (mínimo 30 cm)
	    if (altura < 30) {
	        throw new IllegalArgumentException("La altura parece ser incorrecta (muy pequeña)");
	    }
	    
	    // Validar que la altura no sea demasiado grande (máximo 300 cm)
	    if (altura > 300) {
	        throw new IllegalArgumentException("La altura parece ser incorrecta (muy grande)");
	    }
	    
	    double alturaEnMetros = altura / 100;
	    return peso / (alturaEnMetros * alturaEnMetros);
	}
	
	
	@Override
    public String obtenerCategoriaIMC() {
        double imc = calculaIndiceMasaCorporal();
        if (imc < 18.5) return "Bajo peso";
        else if (imc < 25) return "Normal";
        else if (imc < 30) return "Sobrepeso";
        else return "Obesidad";
    }
	
}
