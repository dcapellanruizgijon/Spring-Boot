package ejercicios;

public class EjercicioA {

	public static void main(String[] args) {
		contarNumero(-2, 2);
	}

	public static void contarNumero(int a, int b) {
		
		if (a == b) {
			System.out.println("Son iguales.");
		}
		if (a > b) {
			for (int i = b; i <= a; i++) {
				System.out.println(i + " ");
			}
		} else {
			for (int i = a; i <= b; i++) {
				System.out.println(i + " ");
			}
		}
	}
}
