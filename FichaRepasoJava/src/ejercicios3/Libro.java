package ejercicios3;

public class Libro {
	private int iSBN;
	private String titulo;
	private String autor;
	private int numeroPaginas;
	
	public Libro(int iSBN, String titulo, String autor, int numeroPaginas) {
		super();
		this.iSBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}

	public Libro() {
		super();
	}

	public int getiSBN() {
		return iSBN;
	}

	public void setiSBN(int iSBN) {
		this.iSBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	@Override
	public String toString() {
		return "El libro " + titulo +" con ISBN: " + iSBN + " creado por el autor " + autor + " tiene " + numeroPaginas + " páginas";
	}
	
	
}
