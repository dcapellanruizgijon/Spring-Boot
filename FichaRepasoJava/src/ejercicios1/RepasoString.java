package ejercicios1;

public class RepasoString {

	public static void main(String[] args) {

		/*
		String texto = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.";

		String partes[] = texto.split("\\.");
		for (int i = 0; i < partes.length; i++) {
			System.out.println(partes[i]);
		}
		System.out.println(partes.length);
		*/
		String texto = "24/9/25";
		
		String partes[] = texto.split("/");
		for (int i = 0; i < partes.length; i++) {
			System.out.println(partes[i]);
		}
		System.out.println(partes[2].length());
		
		System.out.println(Integer.parseInt("t09"));
		/*
		String texto2 = "hola que tal";
		System.out.println(texto2.substring(3, 7));
		*/
	}

}
