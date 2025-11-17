package ejercicios0;

import java.util.Scanner;

public class EjercicioC {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.println("Calcular el área de un: " + "\n1. Cuadrado." + "\n2. Triángulo." + "\n3. Circulo.");

		switch (s.nextInt()) {
		case 1:
			System.out.println("Lado del cuadrado: ");
			System.out.println(areaCuadrado(s.nextInt()));
			break;
		case 2:
			System.out.println("Altura del triangulo: ");
			int altura = s.nextInt();
			System.out.println("Base del triangulo: ");
			int base = s.nextInt();
			System.out.println(areaTriangulo(altura, base));
			break;
		case 3:
			System.out.println("Radio del circulo: ");
			System.out.println(areaCirculo(s.nextInt()));
			break;
		default:
			System.out.println("Valor incorrecto.");
		}
		s.close();
	}

	static int areaCuadrado(int l) {
		// Area cuadrado: (lado * lado)
		return l * l;
	}

	static double areaTriangulo(double a, double b) {
		// Area triangulo: (altura * base) / 2
		return (a * b) / 2;
	}

	static double areaCirculo(double r) {
		// Area circulo: radio * radio * PI
		return r * r * Math.PI;
	}
}
