package Excepciones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class manejoExcepciones {

	public static void main(String[] args) {
System.out.println("=== MANEJO DE EXCEPCIONES ===");
        
        // EXCEPCIÓN SIMPLE CON TRY-CATCH
        try {
            int resultado = 10 / 0; // ArithmeticException
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero");
            System.out.println("Mensaje: " + e.getMessage());
        }
        
        // MULTIPLES CATCH
        try {
            int[] numeros = {1, 2, 3};
            System.out.println(numeros[5]); // ArrayIndexOutOfBoundsException
            int division = 10 / 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (ArithmeticException e) {
            System.out.println("Error: Aritmético");
        }
        
        // CATCH GENÉRICO
        try {
            String texto = null;
            System.out.println(texto.length()); // NullPointerException
        } catch (Exception e) { // Captura cualquier excepción
            System.out.println("Error genérico: " + e.getClass().getSimpleName());
        }
        
        // BLOQUE FINALLY (siempre se ejecuta)
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.print("Ingresa un número: ");
            int numero = scanner.nextInt();
            System.out.println("Número ingresado: " + numero);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número válido");
        } finally {
            if (scanner != null) {
                scanner.close(); // Siempre cerrar recursos
                System.out.println("Scanner cerrado");
            }
        }
        
        // TRY-WITH-RESOURCES (Java 7+)
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingresa texto: ");
            String texto = sc.nextLine();
            System.out.println("Texto: " + texto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } // Scanner se cierra automáticamente
        
        
        // PROPAGACIÓN DE EXCEPCIONES
        try {
            metodoQueLanzaExcepcion();
        } catch (IOException e) {
            System.out.println("Excepción capturada en main: " + e.getMessage());
        }
        
        
        // EXCEPCIONES PERSONALIZADAS
        try {
            validarEdad(15);
        } catch (EdadInvalidaException e) {
            System.out.println("Excepción personalizada: " + e.getMessage());
        }
        
        // THROWS EN DECLARACIÓN DE MÉTODOS
        try {
            leerArchivo("archivo.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        
        System.out.println("\n--- JERARQUÍA EXCEPCIONES ---");
        System.out.println("Throwable (padre de todas)");
        System.out.println("  ├── Error (errores graves: OutOfMemoryError, StackOverflowError)");
        System.out.println("  └── Exception (excepciones recuperables)");
        System.out.println("        ├── RuntimeException (unchecked)");
        System.out.println("        │     ├── NullPointerException");
        System.out.println("        │     ├── ArrayIndexOutOfBoundsException");
        System.out.println("        │     ├── IllegalArgumentException");
        System.out.println("        │     └── ArithmeticException");
        System.out.println("        └── Otras (checked)");
        System.out.println("              ├── IOException");
        System.out.println("              ├── FileNotFoundException");
        System.out.println("              └── SQLException");
    }
    
    // MÉTODO QUE PROPAGA EXCEPCIÓN (throws)
    public static void metodoQueLanzaExcepcion() throws IOException {
        throw new IOException("Error de E/S simulado");
    }
    
    // MÉTODO CON EXCEPCIÓN PERSONALIZADA
    public static void validarEdad(int edad) throws EdadInvalidaException {
        if (edad < 18) {
            throw new EdadInvalidaException("La edad debe ser mayor o igual a 18");
        }
        System.out.println("Edad válida: " + edad);
    }
    
    // MÉTODO CON MULTIPLES EXCEPCIONES
    public static void leerArchivo(String nombre) 
            throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(nombre);
        // Procesar archivo...
        reader.close();
    }
    
    // USO DE throw PARA LANZAR EXCEPCIONES EXPLÍCITAMENTE
    public static double dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor no puede ser cero");
        }
        return (double) a / b;
    }
}

// EXCEPCIÓN PERSONALIZADA
class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}

/*
TIPOS DE EXCEPCIONES:

CHECKED EXCEPTIONS (Verificadas):
- Deben ser declaradas con throws o manejadas con try-catch
- Ejemplos: IOException, SQLException, FileNotFoundException
- Son verificadas en tiempo de compilación

UNCHECKED EXCEPTIONS (No verificadas):
- Subclases de RuntimeException
- No obligatorio declarar o manejar
- Ejemplos: NullPointerException, ArrayIndexOutOfBoundsException

BUENAS PRÁCTICAS:
1. Usar try-with-resources para recursos autocerrables
2. Capturar excepciones específicas antes que las genéricas
3. No usar excepciones para control de flujo normal
4. Registrar excepciones adecuadamente
5. Limpiar recursos en finally o usar try-with-resources

BLOQUES:
- try: Código que puede lanzar excepciones
- catch: Manejo de excepciones específicas
- finally: Código que siempre se ejecuta (limpieza)
- throw: Lanzar excepción explícitamente
- throws: Declarar excepciones que un método puede lanzar
*/