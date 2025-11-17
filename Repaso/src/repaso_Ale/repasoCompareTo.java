package repaso_Ale;

public class repasoCompareTo implements Comparable<repasoCompareTo>{

	// TODO Auto-generated method stub
	private int iSBN;
	private String titulo;
	private String autor;
	private int numeroPaginas;

	public repasoCompareTo(int iSBN, String titulo, String autor, int numeroPaginas) {
			super();
			this.iSBN = iSBN;
			this.titulo = titulo;
			this.autor = autor;
			this.numeroPaginas = numeroPaginas;
		}

	public repasoCompareTo() {
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
		return "El libro " + titulo + " con ISBN: " + iSBN + " creado por el autor " + autor + " tiene " + numeroPaginas
				+ " páginas";
	}

	@Override
	public int compareTo(repasoCompareTo o) {
		// de mayor a menor
		return o.getNumeroPaginas() - getNumeroPaginas();
		// de menor a mayor
		// return getNumeroPaginas() - o.getNumeroPaginas();
	}

}
