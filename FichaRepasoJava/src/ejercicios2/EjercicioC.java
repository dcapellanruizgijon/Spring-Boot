package ejercicios2;

public class EjercicioC {
	
	public static void mostrarMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Crea una matriz “marco” de tamaño 8x6: todos sus elementos deben ser 0 salvo
		 * los de los bordes que deben ser 1. Muestra el resultado
		 */
		int[][] marco = new int[8][6];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if (i == 0 || i == 7 || j == 0 || j == 5) {
					marco[i][j] = 1;
				} else {
					marco[i][j] = 0;					
				}
			}
		}
		mostrarMatriz(marco);
	}

}
