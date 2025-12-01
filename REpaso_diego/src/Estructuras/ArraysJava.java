package Estructuras;

/**
 * PÁGINA 1: ARRAYS EN JAVA
 * Estructura de tamaño fijo
 */

import java.util.Arrays;

public class ArraysJava {
    public static void main(String[] args) {
        System.out.println("=== ARRAYS (Tamaño Fijo) ===");
        
        // DECLARACIONES
        int[] numeros1 = new int[5];          // Array de 5 elementos (0-4)
        int[] numeros2 = {1, 2, 3, 4, 5};     // Inicialización directa
        String[] nombres = {"Ana", "Luis", "Maria"};
        
        // ACCESO Y MODIFICACIÓN
        numeros1[0] = 10;          // Asignar valor en posición 0
        numeros1[1] = 20;
        int valor = numeros1[0];   // Leer valor
        numeros1[0] = 100;         // Modificar valor
        
        // PROPIEDAD IMPORTANTE
        int longitud = numeros1.length;  // length es PROPIEDAD, no método
        
        // RECORRIDO CON FOR TRADICIONAL
        System.out.println("Recorrido con for:");
        for (int i = 0; i < numeros1.length; i++) {
            System.out.println("Índice " + i + ": " + numeros1[i]);
        }
        
        // RECORRIDO CON FOREACH (solo lectura)
        System.out.println("Recorrido con foreach:");
        for (int numero : numeros1) {
            System.out.println("Número: " + numero);
        }
        
        // RECORRIDO CON FOREACH para Strings
        for (String nombre : nombres) {
            System.out.println("Nombre: " + nombre);
        }
        
        // ARRAYS BIDIMENSIONALES
        int[][] matriz = new int[3][3];
        matriz[0][0] = 1;
        matriz[1][1] = 2;
        matriz[2][2] = 3;
        
        // Recorrido matriz bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        
        // MÉTODOS ÚTILES DE LA CLASE Arrays
        int[] desordenado = {5, 2, 8, 1, 9};
        
        Arrays.sort(desordenado);              // Ordenar array
        int[] copia = Arrays.copyOf(desordenado, 3); // Copiar parte
        boolean iguales = Arrays.equals(numeros1, numeros2); // Comparar
        String arrayString = Arrays.toString(desordenado); // Convertir a string
        Arrays.fill(numeros1, 0);              // Llenar con valor
        
        System.out.println("Array ordenado: " + Arrays.toString(desordenado));
        System.out.println("Copia: " + Arrays.toString(copia));
        
        // BÚSQUEDA EN ARRAY ORDENADO
        int[] ordenado = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int indice = Arrays.binarySearch(ordenado, 5); // Devuelve índice de 5
        
        // VENTAJAS Y DESVENTAJAS
        System.out.println("\n--- CARACTERÍSTICAS ARRAYS ---");
        System.out.println("✓ VENTAJAS: Muy rápidos, uso directo de memoria");
        System.out.println("✗ DESVENTAJAS: Tamaño fijo, no pueden crecer");
        System.out.println("✗ DIFICULTAD: Insertar/eliminar elementos complejo");
    }
}

/*
MÉTODOS PRINCIPALES DE Arrays.toString():
- Arrays.sort(array) → Ordena el array
- Arrays.toString(array) → Convierte a string legible
- Arrays.copyOf(array, nuevoTamanno) → Copia array
- Arrays.equals(array1, array2) → Compara arrays
- Arrays.fill(array, valor) → Rellena con valor
- Arrays.binarySearch(array, valor) → Búsqueda binaria (array debe estar ordenado)

PROPIEDADES:
- .length → Devuelve el tamaño (NO es método, es propiedad)

USOS RECOMENDADOS:
- Cuando sabes el tamaño exacto de antemano
- Para operaciones que requieren máximo rendimiento
- Para estructuras de tamaño constante
*/
