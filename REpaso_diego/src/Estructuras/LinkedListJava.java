package Estructuras;

/**
 * PÁGINA 3: LINKEDLIST EN JAVA
 * Lista doblemente enlazada
 */

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListJava {
	
    public static void main(String[] args) {
    	
    	
        System.out.println("=== LINKEDLIST (Lista Enlazada) ===");
        
        // DECLARACIONES
        LinkedList<String> lista = new LinkedList<>();
        
        // MÉTODOS DE LISTA (compartidos con ArrayList)
        lista.add("Elemento 1");      // Añadir al final
        lista.add("Elemento 2");
        lista.add(1, "Entre 1 y 2"); // Añadir en posición
        
        // MÉTODOS ESPECÍFICOS DE LINKEDLIST - AÑADIR
        lista.addFirst("Primero");    // Añadir al inicio
        lista.addLast("Último");      // Añadir al final
        lista.offer("Offer");         // Añadir al final (para colas)
        lista.offerFirst("Offer First"); // Añadir al inicio
        lista.offerLast("Offer Last");   // Añadir al final
        lista.push("Push");           // Añadir al inicio (para pilas)
        
        // MÉTODOS ESPECÍFICOS - OBTENER
        String primero = lista.getFirst();    // Primer elemento
        String ultimo = lista.getLast();      // Último elemento
        String peek = lista.peek();           // Ver primero sin remover
        String peekFirst = lista.peekFirst(); // Ver primero sin remover
        String peekLast = lista.peekLast();   // Ver último sin remover
        String elemento = lista.get(2);       // Obtener por índice
        
        // MÉTODOS ESPECÍFICOS - REMOVER
        String removidoFirst = lista.removeFirst(); // Remover y obtener primero
        String removidoLast = lista.removeLast();   // Remover y obtener último
        String poll = lista.poll();           // Remover y obtener primero
        String pollFirst = lista.pollFirst(); // Remover y obtener primero
        String pollLast = lista.pollLast();   // Remover y obtener último
        String pop = lista.pop();             // Remover y obtener primero (pila)
        
        // MÉTODOS COMUNES CON ArrayList
        boolean contiene = lista.contains("Elemento 1");
        int tamaño = lista.size();
        int indice = lista.indexOf("Elemento 2");
        boolean vacia = lista.isEmpty();
        lista.set(0, "Modificado");          // Reemplazar elemento
        
        // RECORRIDOS
        System.out.println("Recorrido con for:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + ": " + lista.get(i));
        }
        
        System.out.println("Recorrido con foreach:");
        for (String element : lista) {
            System.out.println("Elemento: " + element);
        }
        
        System.out.println("Recorrido con Iterator descendente:");
        Iterator<String> descendente = lista.descendingIterator();
        while (descendente.hasNext()) {
            System.out.println("Elemento: " + descendente.next());
        }
        
        // USO COMO PILA (LIFO)
        System.out.println("--- USO COMO PILA ---");
        LinkedList<String> pila = new LinkedList<>();
        pila.push("Primero");    // Apilar
        pila.push("Segundo");
        pila.push("Tercero");
        System.out.println("Cima: " + pila.peek()); // Ver cima
        System.out.println("Desapilar: " + pila.pop()); // Desapilar
        
        // USO COMO COLA (FIFO)
        System.out.println("--- USO COMO COLA ---");
        LinkedList<String> cola = new LinkedList<>();
        cola.offer("Primero");   // Encolar
        cola.offer("Segundo");
        cola.offer("Tercero");
        System.out.println("Frente: " + cola.peek()); // Ver frente
        System.out.println("Desencolar: " + cola.poll()); // Desencolar
        
        // CONVERSIÓN A ARRAY
        String[] array = lista.toArray(new String[0]);
        
        System.out.println("\n--- CARACTERÍSTICAS LINKEDLIST ---");
        System.out.println("✓ VENTAJAS: Inserción/eliminación rápidas en cualquier posición O(1)");
        System.out.println("✓ VENTAJAS: Puede usarse como pila, cola o lista");
        System.out.println("✗ DESVENTAJAS: Acceso lento por índice O(n)");
        System.out.println("✗ DESVENTAJAS: Mayor uso de memoria (almacena enlaces)");
    }
}

/*
MÉTODOS ESPECÍFICOS DE LinkedList:
- addFirst() / addLast() → Añadir al inicio/final
- removeFirst() / removeLast() → Eliminar del inicio/final
- getFirst() / getLast() → Obtener primero/último
- offer() / offerFirst() / offerLast() → Añadir (para colas)
- poll() / pollFirst() / pollLast() → Eliminar y obtener (colas)
- push() / pop() → Apilar/desapilar (pilas)
- peek() / peekFirst() / peekLast() → Ver sin remover
- descendingIterator() → Iterador en orden inverso

CARACTERÍSTICAS:
- Inserción/eliminación rápidas O(1) en cualquier posición
- Acceso lento por índice O(n) (debe recorrer desde inicio/fin)
- Mayor consumo de memoria por nodo (almacena dos enlaces por elemento)
- Implementa tanto List como Deque

USOS RECOMENDADOS:
- Cuando hay muchas inserciones/eliminaciones en medio de la lista
- Para implementar pilas y colas
- Cuando el acceso secuencial es más común que el acceso aleatorio
*/
