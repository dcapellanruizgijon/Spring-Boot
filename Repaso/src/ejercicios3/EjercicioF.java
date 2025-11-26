package ejercicios3;

import java.util.Iterator;

public class EjercicioF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Boli[] bolis = {
				new Boli("Pilot", "SuperGrip", 1),
				new Boli("Pilot", "G2", 1.3),
				new Boli("Bic", "Cristal", 0.5),
				new Boli("Pilot", "G2", 1.3)
		};
		
		for (Boli boli : bolis) {
			System.out.println(boli.getMarca() + " " + boli.getTipo() + "\tPrecio: " + String.valueOf(boli.getPrecio()).replace('.', ',') + "€");
		}
		
		System.out.println("");
		
		for (int i = 0 ; i < bolis.length - 1; i++) {
			for (int j = 0; j < bolis.length - 1;j++) {
				// compareToIgnoreCase devuelve un int, si es negativo es que es mayor alfabeticamente
				// por eso luego pregunto si es menor a 0 ( < 0 )
				if (bolis[j].getMarca().compareToIgnoreCase(bolis[j + 1].getMarca()) < 0) {
					Boli aux = bolis[j];
					bolis[j] = bolis[j + 1];
					bolis[j + 1] = aux;
				}
			}
		}
		
		for (Boli boli : bolis) {
			System.out.println(boli.getMarca() + " " + boli.getTipo() + "\tPrecio: " + String.valueOf(boli.getPrecio()).replace('.', ',') + "€");
		}
	}

}
