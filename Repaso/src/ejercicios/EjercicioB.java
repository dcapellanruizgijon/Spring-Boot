package ejercicios;

public class EjercicioB {

	public static void sumaImpares(int a) {
		int resultado = 0;
		int num = 1;
		for (int i = 0; i < a; i++) {
			resultado += num;
			num += 2;
		}
		System.out.println("La suma es de: " + resultado);
	}

	public static void main(String[] args) {
		sumaImpares(7);
	}

}
