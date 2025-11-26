package enumJava;

public class enumsJava {
    
    // ENUM BÁSICO
    public enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
    
    // ENUM CON PROPIEDADES Y MÉTODOS
    public enum Planeta {
        MERCURIO(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        TIERRA(5.976e+24, 6.37814e6),
        MARTE(6.421e+23, 3.3972e6);
        
        private final double masa;    // en kilogramos
        private final double radio;   // en metros
        
        // Constructor (siempre privado en enums)
        Planeta(double masa, double radio) {
            this.masa = masa;
            this.radio = radio;
        }
        
        // Métodos
        public double getMasa() { return masa; }
        public double getRadio() { return radio; }
        
        public double gravedadSuperficial() {
            final double G = 6.67300E-11;
            return G * masa / (radio * radio);
        }
    }
    
    // ENUM CON COMPORTAMIENTO ESPECÍFICO
    public enum Operacion {
        SUMA("+") {
            public double aplicar(double x, double y) { return x + y; }
        },
        RESTA("-") {
            public double aplicar(double x, double y) { return x - y; }
        },
        MULTIPLICACION("*") {
            public double aplicar(double x, double y) { return x * y; }
        },
        DIVISION("/") {
            public double aplicar(double x, double y) { return x / y; }
        };
        
        private final String simbolo;
        
        Operacion(String simbolo) {
            this.simbolo = simbolo;
        }
        
        public String getSimbolo() { return simbolo; }
        
        // Método abstracto que cada constante debe implementar
        public abstract double aplicar(double x, double y);
    }
    
    public static void main(String[] args) {
        System.out.println("=== ENUMS EN JAVA ===");
        
        // USO BÁSICO
        DiaSemana hoy = DiaSemana.LUNES;
        DiaSemana mañana = DiaSemana.MARTES;
        
        // COMPARACIÓN
        boolean igual = hoy == DiaSemana.LUNES;        // true
        boolean igual2 = hoy.equals(DiaSemana.LUNES);  // true
        
        // SWITCH CON ENUM
        switch (hoy) {
            case LUNES:
                System.out.println("Inicio de semana");
                break;
            case VIERNES:
                System.out.println("¡Viernes!");
                break;
            default:
                System.out.println("Día normal");
        }
        
        // ITERACIÓN SOBRE ENUMS
        System.out.println("Días de la semana:");
        for (DiaSemana dia : DiaSemana.values()) {
            System.out.println("- " + dia);
        }
        
        // CONVERSIÓN
        String nombre = hoy.name();                    // "LUNES"
        DiaSemana desdeString = DiaSemana.valueOf("MIERCOLES"); // Convierte desde String
        int ordinal = hoy.ordinal();                   // 0 (posición)
        
        // ENUM CON PROPIEDADES
        Planeta tierra = Planeta.TIERRA;
        System.out.println("Masa de la Tierra: " + tierra.getMasa());
        System.out.println("Gravedad en Tierra: " + tierra.gravedadSuperficial());
        
        // ENUM CON COMPORTAMIENTO
        double resultado = Operacion.SUMA.aplicar(5, 3); // 8.0
        System.out.println("5 " + Operacion.SUMA.getSimbolo() + " 3 = " + resultado);
        
        // MÉTODOS ÚTILES
        DiaSemana[] todosDias = DiaSemana.values();    // Array con todas las constantes
        Class<DiaSemana> tipo = DiaSemana.class;       // Class object del enum
        
        // EJEMPLOS PRÁCTICOS
        System.out.println("\n--- EJEMPLOS PRÁCTICOS ---");
        System.out.println("Hoy es: " + hoy);
        System.out.println("Nombre: " + nombre);
        System.out.println("Ordinal: " + ordinal);
        System.out.println("¿Es fin de semana? " + esFinDeSemana(hoy));
        
        // USO EN COLECCIONES
        java.util.EnumSet<DiaSemana> semanaLaboral = 
            java.util.EnumSet.range(DiaSemana.LUNES, DiaSemana.VIERNES);
        
        java.util.EnumMap<DiaSemana, String> actividades = 
            new java.util.EnumMap<>(DiaSemana.class);
        actividades.put(DiaSemana.LUNES, "Reunión de equipo");
    }
    
    // MÉTODO QUE USA ENUM
    public static boolean esFinDeSemana(DiaSemana dia) {
        return dia == DiaSemana.SABADO || dia == DiaSemana.DOMINGO;
    }
}

/*
CARACTERÍSTICAS DE ENUMS:
- Tipo seguro: evita valores inválidos
- Singleton: cada constante es una única instancia
- Pueden tener constructores, métodos y propiedades
- Pueden implementar interfaces
- Útiles para conjuntos de valores constantes

MÉTODOS PRINCIPALES:
- values() → Array con todas las constantes
- valueOf(String) → Convierte String a enum
- name() → Nombre de la constante
- ordinal() → Posición en la declaración
- compareTo() → Comparación por ordinal

VENTAJAS:
- Type-safe: el compilador verifica los valores
- Autocompletado en IDEs
- Pueden tener comportamiento
- Mejor legibilidad del código

USOS RECOMENDADOS:
- Conjuntos de constantes relacionadas
- Cuando los valores son fijos y conocidos
- Para reemplazar constantes mágicas
- Para implementar el patrón Strategy
*/

