package repaso_Ale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class NumerosArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> numeros = new ArrayList<>();
		
		numeros.add("hola");
		numeros.add("adios");
		numeros.add("sandia");
		numeros.add("5");
		// de menor a mayor
		Collections.sort(numeros);
		
		// le damos la vuelta, asi esta de mayor a menor
		Collections.reverse(numeros);
		
		// 'Baraja' los valores
		Collections.shuffle(numeros);
		
		// Vacia el ArrayList
		numeros.clear();
		
		for (String cadaNumero: numeros) {
			System.out.println(cadaNumero);
		} 
	}

}
