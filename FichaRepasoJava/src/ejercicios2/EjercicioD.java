package ejercicios2;

import java.util.Scanner;

public class EjercicioD {

	public static void mostrarMatriz(int[] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.print(matriz[i] + " ");
		}
	}
	public static int[] ordenarTabla(int[] tabla) {
		int aux;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tabla[j + 1] < tabla[j]) {
					aux = tabla[j];
					tabla[j] = tabla[j + 1];
					tabla[j + 1] = aux;
				}
			}
		}
		return tabla;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Tabla de 1 dimensión: Pide 5 números que se introducirán ordenados de forma
		 * creciente. Éstos se guardan en una tabla de tamaño 10. A continuación se pide
		 * un número N, el cual debe insertarse en el lugar adecuado para que la tabla
		 * continúe ordenada
		 */
		Scanner s = new Scanner(System.in);

		int[] tabla = new int[10];

		for (int i = 0; i < 5; i++) {
			System.out.println("Introduce el " + (i + 1) + "º número: ");
			tabla[i] = s.nextInt();
		}
		ordenarTabla(tabla);
		
		System.out.println("Tabla ordenada: ");
		mostrarMatriz(tabla);
		
		for (int i = 6; i < 10; i++) {
			System.out.println("\n\nIntroduce un " + (i + 1) + "º para meterlo en la tabla y que se ordene: ");
			tabla[i] = s.nextInt();
		}
		mostrarMatriz(tabla);
	}

}