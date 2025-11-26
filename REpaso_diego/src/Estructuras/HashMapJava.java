package Estructuras;

/**
 * PÁGINA 4: HASHMAP EN JAVA
 * Estructura clave-valor
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class HashMapJava {
    public static void main(String[] args) {
        System.out.println("=== HASHMAP (Clave-Valor) ===");
        
        // DECLARACIONES
        HashMap<String, Integer> mapa1 = new HashMap<>();          // Vacío
        HashMap<String, String> mapa2 = new HashMap<>(20);         // Capacidad inicial
        HashMap<String, Integer> mapa3 = new HashMap<>(20, 0.75f); // Capacidad y factor carga
        
        // MÉTODOS PARA AÑADIR ELEMENTOS
        mapa1.put("Ana", 25);              // Añadir clave "Ana" con valor 25
        mapa1.put("Luis", 30);
        mapa1.put("Maria", 28);
        mapa1.put("Ana", 26);              // SOBREESCRIBE el valor anterior (25 → 26)
        
        mapa1.putIfAbsent("Juan", 35);     // Solo pone si la clave NO existe
        mapa1.putIfAbsent("Ana", 40);      // No hace nada (Ana ya existe)
        
        // MÉTODOS PARA OBTENER VALORES
        int edadAna = mapa1.get("Ana");              // Obtener valor por clave
        Integer edadPedro = mapa1.get("Pedro");      // Devuelve null (no existe)
        Integer edadDefault = mapa1.getOrDefault("Pedro", 0); // Devuelve 0 si no existe
        
        // VERIFICACIONES
        boolean existeClave = mapa1.containsKey("Ana");      // Verificar clave
        boolean existeValor = mapa1.containsValue(30);       // Verificar valor
        boolean vacio = mapa1.isEmpty();                     // Ver si está vacío
        int tamaño = mapa1.size();                           // Número de entradas
        
        // ELIMINACIÓN
        Integer edadEliminada = mapa1.remove("Luis");        // Elimina por clave
        boolean fueEliminado = mapa1.remove("Maria", 28);    // Elimina solo si coincide
        
        // MODIFICACIÓN
        mapa1.replace("Ana", 27);               // Reemplaza valor
        mapa1.replace("Ana", 27, 26);           // Reemplaza solo si valor actual es 27
        mapa1.replaceAll((clave, valor) -> valor + 1); // Incrementa todos los valores
        
        // RECORRIDO POR CLAVES
        System.out.println("Recorrido por claves:");
        for (String clave : mapa1.keySet()) {
            System.out.println("Clave: " + clave + ", Valor: " + mapa1.get(clave));
        }
        
        // RECORRIDO POR VALORES
        System.out.println("Recorrido por valores:");
        for (Integer valor : mapa1.values()) {
            System.out.println("Valor: " + valor);
        }
        
        // RECORRIDO POR ENTRADAS (MÁS EFICIENTE)
        System.out.println("Recorrido por entradas:");
        for (Map.Entry<String, Integer> entrada : mapa1.entrySet()) {
            System.out.println("Clave: " + entrada.getKey() + 
                             ", Valor: " + entrada.getValue());
        }
        
        // RECORRIDO CON ITERATOR
        System.out.println("Recorrido con Iterator:");
        Iterator<Map.Entry<String, Integer>> iterator = mapa1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entrada = iterator.next();
            System.out.println("Clave: " + entrada.getKey() + 
                             ", Valor: " + entrada.getValue());
            // iterator.remove(); // Para eliminar durante iteración
        }
        
        // RECORRIDO CON forEach (Java 8+)
        System.out.println("Recorrido con forEach:");
        mapa1.forEach((clave, valor) -> 
            System.out.println("Clave: " + clave + ", Valor: " + valor));
        
        // OBTENER COLECCIONES
        Set<String> claves = mapa1.keySet();           // Conjunto de claves
        Collection<Integer> valores = mapa1.values();  // Colección de valores
        Set<Map.Entry<String, Integer>> entradas = mapa1.entrySet(); // Conjunto de entradas
        
        // OPERACIONES AVANZADAS
        mapa1.compute("Ana", (clave, valor) -> valor + 10); // Ana +10
        mapa1.computeIfAbsent("Nuevo", clave -> 100); // Añade si no existe
        mapa1.computeIfPresent("Ana", (clave, valor) -> valor * 2); // Modifica si existe
        mapa1.merge("Ana", 5, (actual, nuevo) -> actual + nuevo); // Fusiona valores
        
        // LIMPIEZA Y COMPARACIÓN
        HashMap<String, Integer> copia = new HashMap<>(mapa1); // Copia
        boolean sonIguales = mapa1.equals(copia);              // Compara contenido
        mapa1.clear();                                         // Vaciar mapa
        
        System.out.println("\n--- CARACTERÍSTICAS HASHMAP ---");
        System.out.println("✓ VENTAJAS: Acceso rápido O(1) promedio");
        System.out.println("✓ VENTAJAS: Flexibilidad en tipos de claves/valores");
        System.out.println("✗ DESVENTAJAS: No mantiene orden de inserción");
        System.out.println("✗ DESVENTAJAS: No permite claves duplicadas");
    }
}

/*
MÉTODOS PRINCIPALES DE HashMap:
- put(clave, valor) → Añadir o actualizar entrada
- get(clave) → Obtener valor por clave
- remove(clave) → Eliminar entrada
- containsKey(clave) / containsValue(valor) → Verificar existencia
- keySet() → Conjunto de claves
- values() → Colección de valores
- entrySet() → Conjunto de entradas (clave-valor)
- size() / isEmpty() → Tamaño y estado
- clear() → Vaciar mapa

MÉTODOS ESPECIALES:
- getOrDefault() → Obtener con valor por defecto
- putIfAbsent() → Poner solo si no existe
- compute() / computeIfAbsent() / computeIfPresent() → Operaciones computadas
- merge() → Fusionar valores
- replace() / replaceAll() → Reemplazar valores

CARACTERÍSTICAS:
- No permite claves duplicadas (las sobreescribe)
- Permite valores null y una clave null
- No mantiene orden de inserción
- Acceso rápido O(1) en promedio

USOS RECOMENDADOS:
- Para asociaciones clave-valor
- Cuando necesitas búsquedas rápidas por clave
- Para contar frecuencias o agrupar datos
- Cuando no importa el orden de los elementos
*/