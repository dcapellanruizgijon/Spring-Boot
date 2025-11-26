package POO;

import java.util.Objects;

public class POO_Java {
	public static void main(String[] args) {
        System.out.println("=== PROGRAMACIÓN ORIENTADA A OBJETOS ===");
        
        // CREACIÓN DE OBJETOS
        Persona persona1 = new Persona(); // Constructor por defecto
        Persona persona2 = new Persona("Ana", 25); // Constructor con parámetros
        
        // USO DE MÉTODOS
        persona1.setNombre("Juan");
        persona1.setEdad(30);
        persona1.mostrarInfo();
        
        persona2.mostrarInfo();
        
        // HERENCIA
        Estudiante estudiante = new Estudiante("María", 20, "Informática");
        estudiante.mostrarInfo(); // Método heredado
        estudiante.estudiar();    // Método propio
        
        // POLIMORFISMO
        Persona[] personas = {
            new Persona("Carlos", 35),
            new Estudiante("Pedro", 22, "Medicina"),
            new Profesor("Laura", 40, "Matemáticas")
        };
        
        for (Persona p : personas) {
            p.mostrarInfo(); // Comportamiento polimórfico
        }
        
        // ENCAPSULAMIENTO
        persona1.setEdad(-5); // No permitido por validación
        System.out.println("Nombre: " + persona1.getNombre());
        
        // ABSTRACCIÓN
        Circulo circulo = new Circulo(5.0);
        System.out.println("Área círculo: " + circulo.calcularArea());
        System.out.println("Perímetro círculo: " + circulo.calcularPerimetro());
        
        Rectangulo rectangulo = new Rectangulo(4.0, 6.0);
        System.out.println("Área rectángulo: " + rectangulo.calcularArea());
        System.out.println("Perímetro rectángulo: " + rectangulo.calcularPerimetro());
        
        // COMPOSICIÓN
        Direccion direccion = new Direccion("Calle Principal", "Ciudad", "12345");
        Persona personaConDireccion = new Persona("Luis", 28, direccion);
        personaConDireccion.mostrarDireccion();
    }
}

// CLASE BASE
class Persona {
    // ENCAPSULAMIENTO - atributos privados
    private String nombre;
    private int edad;
    private Direccion direccion; // COMPOSICIÓN
    
    // CONSTRUCTORES
    public Persona() {
        this.nombre = "Sin nombre";
        this.edad = 0;
    }
    
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public Persona(String nombre, int edad, Direccion direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }
    
    // MÉTODOS GETTER Y SETTER (encapsulamiento)
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("Nombre no válido");
        }
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 150) {
            this.edad = edad;
        } else {
            System.out.println("Edad no válida: " + edad);
        }
    }
    
    public Direccion getDireccion() {
        return direccion;
    }
    
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    // MÉTODOS DE COMPORTAMIENTO
    public void mostrarInfo() {
        System.out.println("Persona: " + nombre + ", Edad: " + edad);
    }
    
    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
    
    public void mostrarDireccion() {
        if (direccion != null) {
            direccion.mostrar();
        } else {
            System.out.println("No tiene dirección registrada");
        }
    }
    
    // MÉTODO toString (sobreescrito de Object)
    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
    
    // MÉTODO equals (sobreescrito de Object)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return edad == persona.edad && nombre.equals(persona.nombre);
    }
    
    // MÉTODO hashCode (sobreescrito de Object)
    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }
}

// HERENCIA - Estudiante ES UNA Persona
class Estudiante extends Persona {
    private String carrera;
    
    public Estudiante(String nombre, int edad, String carrera) {
        super(nombre, edad); // Llamada al constructor de la clase padre
        this.carrera = carrera;
    }
    
    // MÉTODO ESPECÍFICO DE Estudiante
    public void estudiar() {
        System.out.println(getNombre() + " está estudiando " + carrera);
    }
    
    // SOBREESCRITURA DE MÉTODOS (polimorfismo)
    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llamada al método de la clase padre
        System.out.println("Carrera: " + carrera);
    }
    
    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}

// HERENCIA - Profesor ES UNA Persona
class Profesor extends Persona {
    private String materia;
    
    public Profesor(String nombre, int edad, String materia) {
        super(nombre, edad);
        this.materia = materia;
    }
    
    public void enseñar() {
        System.out.println(getNombre() + " está enseñando " + materia);
    }
    
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Materia: " + materia);
    }
}

// CLASE PARA COMPOSICIÓN
class Direccion {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    
    public Direccion(String calle, String ciudad, String codigoPostal) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }
    
    public void mostrar() {
        System.out.println("Dirección: " + calle + ", " + ciudad + ", " + codigoPostal);
    }
    
    // Getters y setters...
}

// ABSTRACCIÓN - Clase abstracta
abstract class Figura {
    // MÉTODOS ABSTRACTOS (sin implementación)
    public abstract double calcularArea();
    public abstract double calcularPerimetro();
    
    // MÉTODO CONCRETO
    public void mostrarTipo() {
        System.out.println("Soy una figura geométrica");
    }
}

// CLASE CONCRETA QUE EXTIIENDE Figura
class Circulo extends Figura {
    private double radio;
    
    public Circulo(double radio) {
        this.radio = radio;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
    
    @Override
    public void mostrarTipo() {
        System.out.println("Soy un círculo");
    }
}

// CLASE CONCRETA QUE EXTIIENDE Figura
class Rectangulo extends Figura {
    private double ancho;
    private double alto;
    
    public Rectangulo(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
    
    @Override
    public double calcularArea() {
        return ancho * alto;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * (ancho + alto);
    }
    
    @Override
    public void mostrarTipo() {
        System.out.println("Soy un rectángulo");
    }
}

// CLASE CON MÉTODOS Y ATRIBUTOS STATIC
class Utilidades {
    // ATRIBUTO STATIC (compartido por todas las instancias)
    public static int contador = 0;
    
    // BLOQUE STATIC (se ejecuta al cargar la clase)
    static {
        System.out.println("Clase Utilidades cargada");
    }
    
    // MÉTODO STATIC (no necesita instancia)
    public static int sumar(int a, int b) {
        return a + b;
    }
    
    public static void incrementarContador() {
        contador++;
    }
}

/*
LOS 4 PILARES DE LA POO:

1. ENCAPSULAMIENTO:
   - Ocultar detalles internos
   - Atributos privados con getters/setters públicos
   - Control sobre cómo se accede y modifica la información
   - Ejemplo: Validaciones en setters, atributos privados

2. HERENCIA:
   - Crear nuevas clases basadas en clases existentes
   - Reutilización de código
   - Relación "ES-UN" (Estudiante ES UNA Persona)
   - extends para heredar
   - super() para llamar al constructor padre

3. POLIMORFISMO:
   - Mismo método, comportamientos diferentes
   - Sobrescritura (override) en herencia
   - Sobrecarga (overload) en misma clase
   - Permite tratar objetos de subclases como de la superclase

4. ABSTRACCIÓN:
   - Ocultar complejidad, mostrar solo lo esencial
   - Clases abstractas e interfaces
   - Métodos abstractos (sin implementación)
   - Plantillas para clases concretas

CONCEPTOS ADICIONALES:

SOBRECARGA (Overload):
- Mismo nombre de método, diferentes parámetros
- En la MISMA clase
- Diferente firma (tipo, número o orden de parámetros)

SOBRESCRITURA (Override):
- Mismo método en clase padre e hija
- Misma firma (nombre y parámetros)
- @Override annotation
- Permite comportamiento específico en subclases

MODIFICADORES DE ACCESO:

public:      Accesible desde cualquier clase
protected:   Accesible desde mismo paquete y subclases
(default):   Accesible solo desde mismo paquete  
private:     Accesible solo desde la misma clase

PALABRAS CLAVE:

this     → Referencia al objeto actual
super    → Referencia a la clase padre
static   → Pertenece a la clase, no a instancias
final    → No puede ser modificado/heredado/sobrescrito
abstract → No se puede instanciar, debe ser extendida

RELACIONES ENTRE CLASES:

ASOCIACIÓN:
- Relación entre objetos de diferentes clases
- Puede ser uno-a-uno, uno-a-muchos, muchos-a-muchos
- Ejemplo: Estudiante - Curso

COMPOSICIÓN:
- Relación "TENER-UN" fuerte
- El objeto parte no puede existir sin el todo
- Ejemplo: Persona - Dirección

AGREGACIÓN:
- Relación "TENER-UN" débil
- El objeto parte puede existir independientemente
- Ejemplo: Universidad - Estudiante

BUENAS PRÁCTICAS:
1. Usar encapsulamiento siempre (atributos privados)
2. Preferir composición sobre herencia
3. Usar @Override para claridad
4. Mantener cohesión alta en clases
5. Usar nombres descriptivos
6. Seguir principios SOLID
*/