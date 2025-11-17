package Estructuras;

/**
 * PÁGINA 2: ARRAYLIST EN JAVA
 * Lista dinámica basada en array
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayListJava {
	
    public static void main(String[] args) {
        System.out.println("=== ARRAYLIST (Lista Dinámica) ===");
        
        // DECLARACIONES
        ArrayList<String> lista1 = new ArrayList<>();      // Lista vacía
        ArrayList<Integer> lista2 = new ArrayList<>(20);   // Capacidad inicial 20
        ArrayList<String> lista3 = new ArrayList<>(
            Arrays.asList("Ana", "Luis", "Maria")); // Inicialización con valores
        
        // MÉTODOS PARA AÑADIR ELEMENTOS
        lista1.add("Primero");                    // Añadir al final
        lista1.add("Segundo");
        lista1.add(1, "Entre medio");            // Añadir en posición específica
        lista1.addAll(lista3);                   // Añadir todos los elementos de otra lista
        lista1.addAll(1, lista3);                // Añadir en posición específica
        
        // MÉTODOS PARA ACCEDER A ELEMENTOS
        String elemento = lista1.get(0);         // Obtener por índice
        int tamaño = lista1.size();              // Tamaño actual
        int indice = lista1.indexOf("Ana");      // Primer índice del elemento
        int ultimoIndice = lista1.lastIndexOf("Ana"); // Último índice
        boolean contiene = lista1.contains("Luis"); // Verificar si contiene
        boolean vacia = lista1.isEmpty();        // Verificar si está vacía
        
        // MÉTODOS PARA MODIFICAR ELEMENTOS
        lista1.set(0, "Modificado");            // Reemplazar elemento en índice
        String removido = lista1.remove(1);     // Remover por índice
        boolean removidoPorValor = lista1.remove("Ana"); // Remover por valor
        
        // RECORRIDOS DIFERENTES
        System.out.println("Recorrido con for:");
        for (int i = 0; i < lista1.size(); i++) {
            System.out.println(i + ": " + lista1.get(i));
        }
        
        System.out.println("Recorrido con foreach:");
        for (String elmto : lista1) {
            System.out.println("Elemento: " + elmto);
        }
        
        System.out.println("Recorrido con Iterator:");
        Iterator<String> iterator = lista1.iterator();
        while (iterator.hasNext()) {
            String actual = iterator.next();
            System.out.println("Elemento: " + actual);
            // iterator.remove(); // Para eliminar durante iteración
        }
        
        // MÉTODOS DE CONVERSIÓN
        Object[] arrayObjetos = lista1.toArray();           // A array de Objects
        String[] arrayStrings = lista1.toArray(new String[0]); // A array de String
        
        // SUBLISTA
        ArrayList<String> subLista = new ArrayList<>(lista1.subList(1, 3));
        
        // COMPARACIÓN Y LIMPIEZA
        boolean sonIguales = lista1.equals(lista3); // Compara contenido
        lista1.clear();                            // Vaciar lista
        
        
        System.out.println("\n--- CARACTERÍSTICAS ARRAYLIST ---");
        System.out.println("✓ VENTAJAS: Tamaño dinámico, muchos métodos útiles");
        System.out.println("✓ VENTAJAS: Acceso rápido por índice (O(1))");
        System.out.println("✗ DESVENTAJAS: Inserción/eliminación lenta al inicio (O(n))");
        System.out.println("✗ DESVENTAJAS: Mayor consumo de memoria que arrays");
    }
}

/*
MÉTODOS PRINCIPALES DE ArrayList:
- add(elemento) / add(indice, elemento) → Añadir elementos
- get(indice) → Obtener elemento
- set(indice, elemento) → Reemplazar elemento
- remove(indice) / remove(elemento) → Eliminar
- size() → Tamaño actual
- isEmpty() → Ver si está vacía
- contains(elemento) → Ver si contiene elemento
- indexOf(elemento) / lastIndexOf(elemento) → Búsqueda
- clear() → Vaciar lista
- toArray() → Convertir a array

CARACTERÍSTICAS:
- Tamaño dinámico (crece automáticamente)
- Acceso rápido por índice O(1)
- Inserción/eliminación lenta al inicio O(n)
- Inserción rápida al final O(1) (amortizado)

USOS RECOMENDADOS:
- Cuando necesitas tamaño variable
- Para acceso frecuente por índice
- Cuando necesitas muchos métodos útiles
- Para la mayoría de casos donde no se necesita máxima eficiencia
*/
