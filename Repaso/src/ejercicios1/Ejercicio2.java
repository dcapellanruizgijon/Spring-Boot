package ejercicios1;

import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) throws fechaIncorrecta {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		System.out.println("Introduche la fecha 1: ");
		String fecha1 = s.nextLine();
		System.out.println("Introduche la fecha 2: ");
		String fecha2 = s.nextLine();
		
		System.out.println("Fecha 1: " + fecha1);
		System.out.println("Fecha 2: " + fecha2);
		System.out.println("Días de diferencia entre las fechas: " + dias(fecha1, fecha2));
		s.close();
	}

	public static int dias(String fecha1, String fecha2) throws fechaIncorrecta {
		String[] partes1 = comprobarFecha(fecha1);
		String[] partes2 = comprobarFecha(fecha2);
		int totalDias1 = 0, totalDias2 = 0;
		// dias
		totalDias1 += Integer.parseInt(partes1[0]);
		totalDias2 += Integer.parseInt(partes2[0]);
		// meses
		totalDias1 += Integer.parseInt(partes1[1]) * 30;
		totalDias2 += Integer.parseInt(partes2[1]) * 30;
		// años
		totalDias1 += Integer.parseInt(partes1[2]) * 365;
		totalDias2 += Integer.parseInt(partes2[2]) * 365;
		
		if (totalDias1 > totalDias2) {
			return totalDias1 - totalDias2;
		}
		return totalDias2 - totalDias1;
	}

	public static String[] comprobarFecha(String texto) throws fechaIncorrecta {
		if (!texto.contains("/")) {
			throw new fechaIncorrecta("Fecha incorrecta.");
		}
		String[] partes = texto.split("/");
		if (partes.length != 3) {
			throw new fechaIncorrecta("Fecha incorrecta.");
		}
		for (int i = 0; i <= 2; i++) {
			if (partes[i].length() != 2 && i < 2) {
				throw new fechaIncorrecta("Formanto incorrecto. (Ejem: 01/01/2000)");
			}
			if (partes[2].length() != 4) {
				throw new fechaIncorrecta("Formanto incorrecto. (Ejem: 01/01/2000)");
			}
			if (!esInt(partes[i])) {
				throw new fechaIncorrecta("Has introducido texto en la fecha.");
			}
			
		}
		
		int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);

        if (dia < 1 || dia > 30 || mes < 1 || mes > 12) {
            throw new fechaIncorrecta("Día o mes fuera de rango. Usa días del 1 al 30 y meses del 1 al 12.");
        }
        
		return partes;
	}

	static boolean esInt(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
