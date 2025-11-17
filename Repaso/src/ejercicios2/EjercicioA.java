package ejercicios2;

import java.util.Scanner;

public class EjercicioA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Crea una matriz con 5 filas y n columnas (Valor que debe pedirse al usuario). A
			continuación, rellénalo con números aleatorios entre 0 y 10. Para ello debes crear una
			función auxiliar que devuelva el número aleatorio generado y se haga la llamada desde la
			clase Main.
		 * */
		Scanner s = new Scanner(System.in);
		System.out.println("¿Cuántas columnas quieres?");
		int[][] matriz = new int[5][s.nextInt()];
		
		for (int i = 0; i < matriz.length ; i++) {
			for (int j = 0; j < matriz[0].length ;j++) {
				matriz[i][j] = numeroAleatorio();
				System.out.print(matriz[i][j] + " | ");
			}
			System.out.println("");
		}
		
		s.close();
	}
	public static int numeroAleatorio() {
		return (int) (Math.random() * 11);
	}

}
