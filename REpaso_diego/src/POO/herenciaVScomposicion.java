package POO;

import java.util.ArrayList;
import java.util.List;

/**
 * DIFERENCIA ENTRE COMPOSICIÓN Y HERENCIA
 * 
 * HERENCIA: Relación "ES-UN" (is-a)
 * COMPOSICIÓN: Relación "TIENE-UN" (has-a)
 */

public class herenciaVScomposicion {
	
	    public static void main(String[] args) {
	    	
	        System.out.println("=== EJEMPLOS PRÁCTICOS ===");
	        
	        // HERENCIA
	        Coche coche = new Coche();
	        coche.arrancar();  // Heredado de Vehiculo
	        coche.abrirMaletero(); // Específico de Coche
	        
	        // COMPOSICIÓN
	        person persona = new person("Ana");
	        persona.usarCoche(coche);
	        persona.conducir();
	    }
	}

	// =============================================================================
	// HERENCIA - Relación "ES-UN"
	// =============================================================================

	/**
	 * EJEMPLO HERENCIA: Coche ES-UN Vehiculo
	 * Se usa cuando hay una relación jerárquica clara
	 */
	class Vehiculo {
	    protected String marca;
	    protected int velocidad;
	    
	    public void arrancar() {
	        System.out.println("Vehículo arrancado");
	        this.velocidad = 0;
	    }
	    
	    public void acelerar(int incremento) {
	        this.velocidad += incremento;
	        System.out.println("Acelerando a " + this.velocidad + " km/h");
	    }
	    
	    public void frenar() {
	        this.velocidad = 0;
	        System.out.println("Vehículo detenido");
	    }
	}

	// Coche ES-UN Vehiculo (hereda todo de Vehiculo)
	class Coche extends Vehiculo {
	    private int numeroPuertas;
	    private boolean maleteroAbierto;
	    
	    public void abrirMaletero() {
	        this.maleteroAbierto = true;
	        System.out.println("Maletero abierto");
	    }
	    
	    public void cerrarMaletero() {
	        this.maleteroAbierto = false;
	        System.out.println("Maletero cerrado");
	    }
	    
	    // Puede sobreescribir métodos del padre
	    @Override
	    public void acelerar(int incremento) {
	        super.acelerar(incremento);
	        System.out.println("Coche acelerando suavemente");
	    }
	}

	// Moto TAMBIÉN ES-UN Vehiculo
	class Moto extends Vehiculo {
	    private boolean sidecar;
	    
	    public void hacerCaballito() {
	        System.out.println("Haciendo caballito!");
	    }
	    
	    @Override
	    public void acelerar(int incremento) {
	        super.acelerar(incremento);
	        System.out.println("Moto acelerando rápidamente");
	    }
	}

	// =============================================================================
	// COMPOSICIÓN - Relación "TIENE-UN"
	// =============================================================================

	/**
	 * EJEMPLO COMPOSICIÓN: Persona TIENE-UN Coche
	 * Se usa cuando un objeto contiene otro objeto
	 */
	class person {
	    private String nombre;
	    private int edad;
	    private Coche coche;  // COMPOSICIÓN - Persona TIENE-UN Coche
	    private Corazon corazon; // COMPOSICIÓN FUERTE - Persona TIENE-UN Corazon
	    
	    public person(String nombre) {
	        this.nombre = nombre;
	        this.corazon = new Corazon(); // La persona CREA su propio corazón
	    }
	    
	    // Persona puede usar un coche (composición débil)
	    public void usarCoche(Coche coche) {
	        this.coche = coche;
	        System.out.println(nombre + " ahora tiene un coche");
	    }
	    
	    public void conducir() {
	        if (coche != null) {
	            System.out.println(nombre + " está conduciendo");
	            coche.arrancar();
	            coche.acelerar(50);
	        } else {
	            System.out.println(nombre + " no tiene coche para conducir");
	        }
	    }
	    
	    // Método que usa la composición fuerte
	    public void latirCorazon() {
	        corazon.latir();
	    }
	}

	// Corazon es PARTE de Persona (composición fuerte)
	class Corazon {
	    public void latir() {
	        System.out.println("❤️ Corazón latiendo...");
	    }
	}

	// =============================================================================
	// EJEMPLOS MÁS COMPLEJOS
	// =============================================================================

	/**
	 * CASO DONDE HERENCIA NO ES APROPIADA
	 * ❌ MAL DISEÑO: Pato extends Pajaro, Avion extends Pajaro
	 */

	// ❌ DISEÑO INCORRECTO CON HERENCIA
	class Pajaro {
	    public void volar() {
	        System.out.println("Volando como pájaro");
	    }
	    
	    public void cantar() {
	        System.out.println("Cantando como pájaro");
	    }
	}

	// ❌ PROBLEMA: Un avión NO ES-UN pájaro
	class Avion extends Pajaro {  // ¡DISEÑO INCORRECTO!
	    @Override
	    public void cantar() {
	        // ¿Un avión cantando? ¡No tiene sentido!
	        throw new UnsupportedOperationException("Los aviones no cantan");
	    }
	}

	/**
	 * ✅ DISEÑO CORRECTO USANDO COMPOSICIÓN E INTERFACES
	 */
	interface Volador {
	    void volar();
	}

	class PajaroCorrecto implements Volador {
	    @Override
	    public void volar() {
	        System.out.println("Volando batiendo alas");
	    }
	    
	    public void cantar() {
	        System.out.println("♫ Cantando ♫");
	    }
	}

	class AvionCorrecto implements Volador {
	    private Motor motor; // COMPOSICIÓN - Avion TIENE-UN Motor
	    
	    public AvionCorrecto() {
	        this.motor = new Motor();
	    }
	    
	    @Override
	    public void volar() {
	        motor.encender();
	        System.out.println("Volando con motor a reacción");
	    }
	}

	class Motor {
	    public void encender() {
	        System.out.println("Motor encendido");
	    }
	}

	// =============================================================================
	// COMPOSICIÓN vs HERENCIA - DECISIÓN DE DISEÑO
	// =============================================================================

	/**
	 * EJEMPLO PRÁCTICO: Sistema de Empleados
	 */

	// HERENCIA - Cuando la relación ES-UN es clara
	class Empleado {
	    protected String nombre;
	    protected double salarioBase;
	    
	    public Empleado(String nombre, double salarioBase) {
	        this.nombre = nombre;
	        this.salarioBase = salarioBase;
	    }
	    
	    public double calcularSalario() {
	        return salarioBase;
	    }
	}

	// Gerente ES-UN Empleado con características adicionales
	class Gerente extends Empleado {
	    private double bono;
	    
	    public Gerente(String nombre, double salarioBase, double bono) {
	        super(nombre, salarioBase);
	        this.bono = bono;
	    }
	    
	    @Override
	    public double calcularSalario() {
	        return salarioBase + bono;
	    }
	    
	    public void gestionarEquipo() {
	        System.out.println(nombre + " está gestionando el equipo");
	    }
	}

	// COMPOSICIÓN - Cuando un objeto TIENE-OTRO objeto
	class Departamento {
	    private String nombre;
	    private Gerente gerente;  // Departamento TIENE-UN Gerente
	    private List<Empleado> empleados; // Departamento TIENE-MUCHOS Empleados
	    
	    public Departamento(String nombre, Gerente gerente) {
	        this.nombre = nombre;
	        this.gerente = gerente;
	        this.empleados = new ArrayList<>();
	    }
	    
	    public void agregarEmpleado(Empleado empleado) {
	        empleados.add(empleado);
	    }
	    
	    public void mostrarInformacion() {
	        System.out.println("Departamento: " + nombre);
	        System.out.println("Gerente: " + gerente.nombre);
	        System.out.println("Empleados: " + empleados.size());
	    }
	}

	// =============================================================================
	// VENTAJAS Y DESVENTAJAS
	// =============================================================================

	class VentajasDesventajas {
	    public void mostrarComparacion() {
	        System.out.println("\n=== VENTAJAS HERENCIA ===");
	        System.out.println("✅ Reutilización de código");
	        System.out.println("✅ Relación natural (ES-UN)");
	        System.out.println("✅ Polimorfismo fácil");
	        System.out.println("✅ Extensibilidad");
	        
	        System.out.println("\n=== DESVENTAJAS HERENCIA ===");
	        System.out.println("❌ Acoplamiento fuerte");
	        System.out.println("❌ Herencia múltiple no permitida");
	        System.out.println("❌ Fragilidad (cambios en padre afectan hijos)");
	        System.out.println("❌ Puede violar encapsulamiento");
	        
	        System.out.println("\n=== VENTAJAS COMPOSICIÓN ===");
	        System.out.println("✅ Acoplamiento débil");
	        System.out.println("✅ Flexibilidad (puede cambiar en runtime)");
	        System.out.println("✅ Reutilización mediante interfaces");
	        System.out.println("✅ Mejor encapsulamiento");
	        
	        System.out.println("\n=== DESVENTAJAS COMPOSICIÓN ===");
	        System.out.println("❌ Más código boilerplate");
	        System.out.println("❌ Mayor complejidad");
	        System.out.println("❌ Menos intuitiva para relaciones jerárquicas");
	    }
	}

	// =============================================================================
	// REGLA PRÁCTICA: "PREFIERE COMPOSICIÓN SOBRE HERENCIA"
	// =============================================================================

	/**
	 * EJEMPLO DONDE COMPOSICIÓN ES MEJOR QUE HERENCIA
	 */

	// ❌ MAL USO DE HERENCIA
	class StackMalo extends ArrayList<Object> {
	    public void push(Object item) {
	        add(item);
	    }
	    
	    public Object pop() {
	        return remove(size() - 1);
	    }
	    
	    // PROBLEMA: Heredamos métodos que no deberíamos tener
	    // como add(int index, Object element) que rompe LIFO
	}

	// ✅ BUEN USO DE COMPOSICIÓN
	class StackBueno {
	    private List<Object> elementos = new ArrayList<>(); // COMPOSICIÓN
	    
	    public void push(Object item) {
	        elementos.add(item);
	    }
	    
	    public Object pop() {
	        if (elementos.isEmpty()) {
	            throw new RuntimeException("Stack vacío");
	        }
	        return elementos.remove(elementos.size() - 1);
	    }
	    
	    public boolean isEmpty() {
	        return elementos.isEmpty();
	    }
	    
	    // Solo exponemos los métodos que queremos
	    // No hay métodos que violen LIFO
	}

	// =============================================================================
	// TEST PARA ENTENDER LAS DIFERENCIAS
	// =============================================================================

	class TestComposicionHerencia {
	    public static void main(String[] args) {
	        System.out.println("=== TEST PRÁCTICO ===");
	        
	        // HERENCIA - Relación permanente
	        Coche miCoche = new Coche();
	        System.out.println("¿miCoche ES-UN Vehiculo? " + (miCoche instanceof Vehiculo));
	        
	        // COMPOSICIÓN - Relación dinámica
	        person juan = new person("Juan");
	        System.out.println("¿Juan TIENE-UN Coche? " + (juan != null)); // Inicialmente no
	        
	        juan.usarCoche(miCoche);
	        System.out.println("¿Ahora Juan TIENE-UN Coche? " + (juan != null)); // Ahora sí
	        
	        // Puedo cambiar la relación
	        juan.usarCoche(null);
	        System.out.println("¿Juan aún TIENE-UN Coche? " + (juan != null)); // Ya no
	    }
	}

	/*
	RESUMEN FINAL - CUÁNDO USAR CADA UNA:

	USAR HERENCIA CUANDO:
	1. Hay una relación clara "ES-UN"
	2. La subclase es una especialización de la superclase
	3. Quieres reutilizar código de la superclase
	4. Necesitas polimorfismo
	5. La relación es permanente y natural

	USAR COMPOSICIÓN CUANDO:
	1. Hay una relación "TIENE-UN" o "USA-UN"
	2. Quieres reutilizar funcionalidad sin heredar
	3. Necesitas flexibilidad para cambiar comportamiento
	4. Quieres evitar el acoplamiento fuerte
	5. La relación puede cambiar en tiempo de ejecución

	REGLA DE ORO: 
	"Prefiere composición sobre herencia, pero usa herencia cuando la relación ES-UN sea natural y permanente"

	PREGUNTAS PARA DECIDIR:
	1. ¿El objeto B es realmente un tipo de objeto A? → HERENCIA
	2. ¿El objeto A necesita usar funcionalidades de B? → COMPOSICIÓN
	3. ¿Puede el objeto A existir sin B? → COMPOSICIÓN
	4. ¿La relación es permanente? → HERENCIA
	5. ¿Quiero poder cambiar B dinámicamente? → COMPOSICIÓN
	*/