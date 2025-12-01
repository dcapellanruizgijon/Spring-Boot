package repaso_Ale;

import java.util.HashMap;
import java.util.Map;

public class NumerosHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> numeros = new HashMap<>();
		
		numeros.put(1, "Utrera");
		numeros.put(2, "Sevilla");
		numeros.put(3, "Sandia");
		numeros.put(1, "Utrera 5");
		
		for (Map.Entry pareja: numeros.entrySet()) {
			System.out.print(pareja.getKey() + "\t");
			System.out.println(pareja.getValue());
		}
	}

}
