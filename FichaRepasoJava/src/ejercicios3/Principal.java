package ejercicios3;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Libro libro1 = new Libro(1234567, "El Quijote", "Cervantes", 1200);
		Libro libro2 = new Libro(5413252, "El Resplandor", "Stephen King", 700);
		
		System.out.println(libro1);
		System.out.println(libro2);
		
		if (libro1.getNumeroPaginas() > libro2.getNumeroPaginas()) {
			System.out.println(libro1.getTitulo() + " tiene más páginas que " + libro2.getTitulo());
		} else if (libro1.getNumeroPaginas() == libro2.getNumeroPaginas()) {
			System.out.println("Ambos tienen las mismas páginas.");			
		} else {			
			System.out.println(libro2.getTitulo() + " tiene más páginas que " + libro1.getTitulo());
		}
	}

}
