package ejercicios3;
import java.util.Collections; 
import java.util.ArrayList;

public class EjercicioE {

	public static void main(String[] args) {
		ArrayList<Movil> moviles = new ArrayList<>();
		
		moviles.add(new Movil("Google", "Pixel 7", 350));
		moviles.add(new Movil("Samsung", "S25", 1000));
		moviles.add(new Movil("Apple", "Iphone 15", 1200));
		moviles.add(new Movil("Xiaomi", "Mi 10 Pro", 500));
		moviles.add(new Movil("Huawei", "P40 Pro+", 600));
		moviles.add(new Movil("Google", "Pixel 10", 1000));
		
		for (Movil tlf : moviles) {
			System.out.println(tlf);
		}
		
		Collections.sort(moviles);
		
		System.out.println("");
		
		for (Movil tlf : moviles) {
			System.out.println(tlf);
		}
	}
}