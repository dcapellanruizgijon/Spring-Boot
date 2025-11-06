package clases;

public class Ejercicio {

	 private String Nombre;
	    private int series;
	    private int repeticiones;
	    private String descripcion;
	    private boolean completado;
	    
	    public Ejercicio(String nombre, int series, int repeticiones, String descripcion) {
	        this.Nombre = nombre;
	        this.series = series;
	        this.repeticiones = repeticiones;
	        this.descripcion = descripcion;
	        this.completado = false;//para que al crear el objeto sea false por defecto
	    }

	    public String getNombre() {
	        return Nombre;
	    }

	    public void setNombre(String nombre) {
	        Nombre = nombre;
	    }

	    public int getSeries() {
	        return series;
	    }

	    public void setSeries(int series) {
	        this.series = series;
	    }

	    public int getRepeticiones() {
	        return repeticiones;
	    }

	    public void setRepeticiones(int repeticiones) {
	        this.repeticiones = repeticiones;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    public boolean isCompletado() {
	        return completado;
	    }
	    
	    //con este de abajo podremos marcar el ejercicio como completado
	    public void setCompletado(boolean completado) {
	        this.completado = completado;
	    }
	
}
