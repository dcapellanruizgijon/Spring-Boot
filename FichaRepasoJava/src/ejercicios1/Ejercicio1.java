package ejercicios1;

import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		System.out.println("Introduce tu primer texto: ");
		String texto1 = s.nextLine();

		System.out.println("Introduce tu segundo texto: ");
		String texto2 = s.nextLine();

		funcion(texto1, texto2);
		s.close();
	}

	public static void funcion(String str1, String str2) {
		String out1 = "", out2 = "", letra = "";

		for (int i = 0; i < str1.length(); i++) {
			letra = String.valueOf(str1.charAt(i));
			if (str2.contains(letra)) {
				out2 += letra;
			} else {
				out1 += letra;
			}
		}
		System.out.println("Primer String: " + str1);
		System.out.println("Segundo String: " + str2);
		System.out.println("Primera salida: " + out1);
		System.out.println("Segunda salida: " + out2);
	}
}
