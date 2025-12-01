package ejercicios2;

import java.util.Scanner;

public class EjercicioB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Crea 2 matrices de mxm y suma sus valores. Los resultados deben almacenarse
		 * en otra matriz distinta. Los valores y la longitud, seran elegidos por el
		 * usuario. Finalmente, muestra por pantalla las matrices originales y el
		 * resultado, para ello debes crear una función auxiliar que muestre las
		 * matrices y se haga la llamada desde la clase Main.
		 */

		Scanner s = new Scanner(System.in);
		System.out.println("¿Cuántas filas y columnas quieres?");
		int dimension = s.nextInt();

		int[][] matriz1 = new int[dimension][dimension];
		int[][] matriz2 = new int[dimension][dimension];
		
		System.out.println("\nRellena la primera matriz: ");
		matriz1 = rellenarMatriz(dimension);
		System.out.println("\nRellena la segunda matriz: ");
		matriz2 = rellenarMatriz(dimension);

		// Creamos la matriz resultante y le sumamos las otras dos.
		int [][] matriz3 = sumarMatrices(matriz1, matriz2);
		
		System.out.println("\nMatriz 1: ");
		mostrarMatriz(matriz1);
		System.out.println("\nMatriz 1: ");
		mostrarMatriz(matriz2);		
		System.out.println("\nSuma de las matrices: ");
		mostrarMatriz(matriz3);
		s.close();
	}

	public static void mostrarMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " | ");
			}
			System.out.println("");
		}
	}

	public static int[][] rellenarMatriz(int dimension) {
		int[][] matriz1 = new int[dimension][dimension];
		Scanner s = new Scanner(System.in);
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				// i + 1 / j + 1 para que no aparezca como "columna 0"
				System.out.println("Rellena el valor de la fila: " + (i + 1) + " Columna: " + (j + 1));
				matriz1[i][j] = s.nextInt();
			}
		}
		return matriz1;
	}

	public static int[][] sumarMatrices(int[][] matriz1, int[][] matriz2) {
		int[][] matriz3 = new int[matriz1.length][matriz1.length];
		// como son mxm es siempre el mismo valor asi que utilizo siempre matriz1.legth
		for (int i = 0; i < matriz1.length; i++) {
			for (int j = 0; j < matriz1[0].length; j++) {
				matriz3[i][j] = matriz1[i][j] + matriz2[i][j];
			}
		}
		return matriz3;
	}

}
