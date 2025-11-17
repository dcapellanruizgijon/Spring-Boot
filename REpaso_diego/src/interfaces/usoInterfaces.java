package interfaces;

public class usoInterfaces {
	public static void main(String[] args) {
        System.out.println("=== INTERFACES EN JAVA ===");
        
        // USO DE INTERFACES
        Animal perro = new Perro();
        perro.hacerSonido();
        perro.dormir();
        
        Mascota mascota = new Perro();
        mascota.jugar();
        
        // MÚLTIPLES INTERFACES
        Pato pato = new Pato();
        pato.volar();
        pato.nadar();
        pato.hacerSonido();
        
        // MÉTODOS DEFAULT
        perro.respirar(); // Método default de la interfaz
        
        // MÉTODOS ESTÁTICOS EN INTERFACES
        Animal.mostrarInfo();
        
        // POLIMORFISMO CON INTERFACES
        Animal[] animales = {new Perro(), new Gato(), new Pato()};
        for (Animal animal : animales) {
            animal.hacerSonido();
        }
        
        // CASTING CON INTERFACES
        if (perro instanceof Mascota) {
            Mascota m = (Mascota) perro;
            m.jugar();
        }
    }
}

// INTERFAZ BÁSICA
interface Animal {
    // CONSTANTES (implícitamente public static final)
    String TIPO = "Animal";
    
    // MÉTODOS ABSTRACTOS (sin implementación)
    void hacerSonido();
    
    // MÉTODO DEFAULT (Java 8+)
    default void dormir() {
        System.out.println("El animal está durmiendo");
    }
    
    // MÉTODO DEFAULT CON IMPLEMENTACIÓN
    default void respirar() {
        System.out.println("El animal está respirando");
    }
    
    // MÉTODO ESTÁTICO (Java 8+)
    static void mostrarInfo() {
        System.out.println("Interfaz Animal - Todos los animales hacen sonidos");
    }
    
    // MÉTODO PRIVATE (Java 9+)
    private void metodoPrivado() {
        System.out.println("Método privado de la interfaz");
    }
}

// SEGUNDA INTERFAZ
interface Mascota {
    void jugar();
    
    default void pasear() {
        System.out.println("La mascota está paseando");
    }
}

// TERCERA INTERFAZ
interface Volador {
    void volar();
    
    default void aterrizar() {
        System.out.println("Aterrizando...");
    }
}

// CUARTA INTERFAZ
interface Nadador {
    void nadar();
    
    default void flotar() {
        System.out.println("Flotando en el agua");
    }
}

// CLASE QUE IMPLEMENTA UNA INTERFAZ
class Perro implements Animal, Mascota {
    @Override
    public void hacerSonido() {
        System.out.println("Guau guau!");
    }
    
    @Override
    public void jugar() {
        System.out.println("El perro está jugando con la pelota");
    }
    
    // Puede sobreescribir métodos default (opcional)
    @Override
    public void dormir() {
        System.out.println("El perro ronca mientras duerme");
    }
}

// CLASE QUE IMPLEMENTA UNA INTERFAZ
class Gato implements Animal, Mascota {
    @Override
    public void hacerSonido() {
        System.out.println("Miau miau!");
    }
    
    @Override
    public void jugar() {
        System.out.println("El gato está jugando con el ovillo");
    }
}

// CLASE QUE IMPLEMENTA MÚLTIPLES INTERFACES
class Pato implements Animal, Volador, Nadador {
    @Override
    public void hacerSonido() {
        System.out.println("Cuac cuac!");
    }
    
    @Override
    public void volar() {
        System.out.println("El pato está volando");
    }
    
    @Override
    public void nadar() {
        System.out.println("El pato está nadando");
    }
    
    // Puede usar métodos default de todas las interfaces
    public void actividades() {
        volar();
        nadar();
        aterrizar();
        flotar();
    }
}

// INTERFAZ QUE EXTENDE OTRA INTERFAZ
interface AnimalAcuatico extends Animal, Nadador {
    void bucear();
    
    // Hereda todos los métodos de Animal y Nadador
}

class Delfin implements AnimalAcuatico {
    @Override
    public void hacerSonido() {
        System.out.println("Eee eee!");
    }
    
    @Override
    public void nadar() {
        System.out.println("El delfín nada rápidamente");
    }
    
    @Override
    public void bucear() {
        System.out.println("El delfín está buceando");
    }
}

// INTERFAZ FUNCIONAL (Java 8+) - Solo un método abstracto
@FunctionalInterface
interface Calculadora {
    int operar(int a, int b);
    
    // Puede tener métodos default y static
    default void mostrarResultado(int resultado) {
        System.out.println("Resultado: " + resultado);
    }
}

class UsoInterfazFuncional {
    public static void main(String[] args) {
        // Implementación con lambda
        Calculadora suma = (a, b) -> a + b;
        Calculadora resta = (a, b) -> a - b;
        Calculadora multiplicacion = (a, b) -> a * b;
        
        System.out.println("Suma: " + suma.operar(5, 3));
        System.out.println("Resta: " + resta.operar(5, 3));
        
        // Implementación con clase anónima
        Calculadora division = new Calculadora() {
            @Override
            public int operar(int a, int b) {
                return a / b;
            }
        };
        
        System.out.println("División: " + division.operar(10, 2));
    }
}

/*
CARACTERÍSTICAS DE INTERFACES:

ANTES DE JAVA 8:
- Solo métodos abstractos
- Solo constantes (public static final)

JAVA 8:
- Métodos default (con implementación)
- Métodos static

JAVA 9:
- Métodos private

BENEFICIOS:
- Contrato que deben seguir las clases
- Herencia múltiple (una clase puede implementar varias interfaces)
- Acoplamiento débil
- Facilita testing (mocks)
- Permite polimorfismo

INTERFACES FUNCIONALES:
- Solo un método abstracto
- Se pueden usar con expresiones lambda
- Anotadas con @FunctionalInterface

REGLA ORO:
- Usar interfaces para definir capacidades/comportamientos
- Usar clases abstractas para compartir código entre clases relacionadas
*/
