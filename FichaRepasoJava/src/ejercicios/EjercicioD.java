package ejercicios;

public class EjercicioD {

	public static void main(String[] args) {
		convertirBinario(20);
	}
	
	public static void convertirBinario(int a) {
		if (a < 0 || a > 255) {
            System.out.println("El número debe estar entre 0 y 255.");
        } else {
        	String binario = "";
        	
            for (int i = 7; i >= 0; i--) {
                int bit = a % 2;
                binario = bit + binario;
                a /= 2;
            }
            System.out.println(binario);
        }
	}
}
