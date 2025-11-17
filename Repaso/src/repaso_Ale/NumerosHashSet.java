package repaso_Ale;

import java.util.HashSet;

public class NumerosHashSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> numeros = new HashSet<>();
		
		numeros.add("Utrera");
		numeros.add("Sevilla");
		numeros.add("Sandia");
		numeros.add("Los Palacios");
		numeros.add("Los Palacios");
		numeros.add("Los Palacios");
		numeros.add("Los Palacios");
		numeros.add("Los Palacios");
		
		// Vacia el HashSet
		// numeros.clear();
		for (String cadaNumero: numeros) {
			System.out.println(cadaNumero);
		}
	}

}
