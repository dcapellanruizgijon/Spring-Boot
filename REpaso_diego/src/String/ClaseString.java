package String;

import java.util.Arrays;

public class ClaseString {

	public static void main(String[] args) {
		        System.out.println("=== CLASE STRING ===");
		        
		        // DECLARACIONES Y CREACIÓN
		        String str1 = "Hola Mundo";                    // Literal (va al string pool)
		        String str2 = new String("Hola Mundo");        // Objeto explícito
		        char[] chars = {'H', 'o', 'l', 'a'};
		        String str3 = new String(chars);               // Desde array de chars
		        String str4 = String.valueOf(123);             // Desde número
		        
		        // COMPARACIÓN DE STRINGS
		        boolean igual1 = str1.equals(str2);            // Compara CONTENIDO (true)
		        boolean igual2 = str1 == str2;                 // Compara REFERENCIA (false)
		        boolean igual3 = str1.equalsIgnoreCase("HOLA MUNDO"); // Ignora mayúsculas
		        
		        int comparacion = str1.compareTo("Hola");      // Comparación lexicográfica
		        // <0 si str1 es menor, 0 si igual, >0 si mayor
		        
		        // MÉTODOS DE INFORMACIÓN
		        int longitud = str1.length();                  // Longitud del string
		        boolean vacio = str1.isEmpty();                // Ver si está vacío
		        char caracter = str1.charAt(0);                // Carácter en posición
		        
		        // BÚSQUEDA
		        int indice1 = str1.indexOf('o');               // Primera aparición de 'o'
		        int indice2 = str1.indexOf("Mundo");           // Primera aparición de substring
		        int ultimoIndice = str1.lastIndexOf('o');      // Última aparición
		        boolean contiene = str1.contains("Mundo");     // Ver si contiene substring
		        boolean empieza = str1.startsWith("Hola");     // Ver si empieza con
		        boolean termina = str1.endsWith("Mundo");     // Ver si termina con
		        
		        // EXTRACCIÓN Y SUBSTRING
		        String sub1 = str1.substring(5);               // Desde índice 5 hasta final
		        String sub2 = str1.substring(0, 4);            // Desde 0 hasta 4 (exclusivo)
		        char[] arrayChars = str1.toCharArray();        // Convierte a array de chars
		        
		        // MODIFICACIÓN (devuelven nuevo string, NO modifican original)
		        String mayusculas = str1.toUpperCase();        // Todo mayúsculas
		        String minusculas = str1.toLowerCase();        // Todo minúsculas
		        String sinEspacios = "  hola  ".trim();        // Elimina espacios extremos
		        String reemplazado = str1.replace('o', '0');   // Reemplaza caracteres
		        String reemplazado2 = str1.replace("Mundo", "Java"); // Reemplaza substring
		        
		        // DIVISIÓN Y UNIÓN
		        String texto = "manzana,naranja,pera";
		        String[] frutas = texto.split(",");            // Divide por separador
		        String unido = String.join("-", frutas);       // Une con separador
		        
		        // FORMATO
		        String formato1 = String.format("Hola %s, tienes %d años", "Ana", 25);
		        String formato2 = "Hola %s, tienes %d años".formatted("Ana", 25);
		        
		        // CONCATENACIÓN
		        String concatenado1 = str1 + " " + "Java";     // Con operador +
		        String concatenado2 = str1.concat(" Java");    // Con método concat
		        StringBuilder sb = new StringBuilder();
		        sb.append(str1).append(" ").append("Java");    // Con StringBuilder (eficiente)
		        
		        // VERIFICACIÓN
		        boolean esNumero = "123".matches("\\d+");      // Verifica con regex
		        boolean soloLetras = "abc".matches("[a-zA-Z]+");
		        
		        System.out.println("Original: " + str1);
		        System.out.println("Mayúsculas: " + mayusculas);
		        System.out.println("Substring(0,4): " + sub2);
		        System.out.println("Split: " + Arrays.toString(frutas));
		        
		        // MÉTODOS JAVA 11+
		        String repetido = "Java ".repeat(3);           // Repite string
		        String sinEspacios11 = "  hola  ".strip();     // Similar a trim (Unicode-aware)
		        boolean isBlank = "   ".isBlank();             // True si vacío o solo espacios
		        
		        System.out.println("\n--- CARACTERÍSTICAS STRING ---");
		        System.out.println("✓ INMUTABLE: Los strings no se modifican, se crean nuevos");
		        System.out.println("✓ STRING POOL: Los literals se reutilizan");
		        System.out.println("✓ SEGURO PARA HILOS: Por ser inmutable");
		    }
		}

		/*
		MÉTODOS PRINCIPALES STRING:
		- length(), isEmpty(), charAt()
		- equals(), compareTo(), equalsIgnoreCase()
		- indexOf(), lastIndexOf(), contains()
		- substring(), toCharArray()
		- toUpperCase(), toLowerCase(), trim()
		- replace(), split(), join()
		- format(), formatted()
		- startsWith(), endsWith()

		INMUTABILIDAD:
		String original = "hola";
		original.toUpperCase();  // NO modifica original
		String nuevo = original.toUpperCase(); // Crea nuevo string

		STRING BUILDER vs STRING:
		- Usar String para textos que no cambian
		- Usar StringBuilder para construcción eficiente de strings
		- StringBuilder es MUTABLE y más eficiente para concatenaciones múltiples
		*/