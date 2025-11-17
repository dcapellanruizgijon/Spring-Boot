package ejercicios4;

import java.util.ArrayList;

public class EjercicioB {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numeros = new ArrayList<>();
		
		numeros.add(12);
		numeros.add(24);
		numeros.add(48);
		numeros.add(96);
		numeros.add(192);
		numeros.add(384);
		
		for (Integer num : numeros) {
			System.out.println(num);
		}
		
		System.out.println("\n");
		
		numeros = vaciarArrayList(numeros);
				
		for (Integer num : numeros) {
			System.out.println(num);
		}
	}
	public static ArrayList<Integer> vaciarArrayList(ArrayList<Integer> arrayListVaciar) {
		do {
			arrayListVaciar.remove(0);
		} while (!arrayListVaciar.isEmpty());
		return arrayListVaciar;
	}
}
