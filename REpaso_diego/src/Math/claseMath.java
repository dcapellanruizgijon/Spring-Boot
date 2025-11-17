package Math;

public class claseMath {

	public static void main(String[] args) {
        System.out.println("=== CLASE MATH ===");
        
        // CONSTANTES MATEMÁTICAS
        double pi = Math.PI;                   // 3.141592653589793
        double e = Math.E;                     // 2.718281828459045
        
        // MÉTODOS DE REDONDEO
        double numero = 3.7;
        long redondeoAbajo = Math.round(3.2);  // 3 (redondeo matemático)
        long redondeoArriba = Math.round(3.7); // 4
        double piso = Math.floor(3.7);         // 3.0 (siempre hacia abajo)
        double techo = Math.ceil(3.2);         // 4.0 (siempre hacia arriba)
        
        // VALOR ABSOLUTO
        int absInt = Math.abs(-5);             // 5
        double absDouble = Math.abs(-3.14);    // 3.14
        float absFloat = Math.abs(-2.5f);      // 2.5
        
        // MÁXIMOS Y MÍNIMOS
        int max = Math.max(10, 20);            // 20
        int min = Math.min(10, 20);            // 10
        double maxDouble = Math.max(3.14, 2.71); // 3.14
        
        // POTENCIAS Y RAÍCES
        double potencia = Math.pow(2, 3);      // 8.0 (2 elevado a 3)
        double raizCuadrada = Math.sqrt(16);   // 4.0
        double raizCubica = Math.cbrt(27);     // 3.0
        double exponencial = Math.exp(1);      // e^1 ≈ 2.718
        double logNatural = Math.log(Math.E);  // 1.0 (logaritmo natural)
        double logBase10 = Math.log10(100);    // 2.0
        
        // TRIGONOMETRÍA (trabajan en radianes)
        double angulo = Math.PI / 4;           // 45° en radianes
        double seno = Math.sin(angulo);        // sen(45°) ≈ 0.707
        double coseno = Math.cos(angulo);      // cos(45°) ≈ 0.707
        double tangente = Math.tan(angulo);    // tan(45°) ≈ 1.0
        
        // CONVERSIÓN GRADOS ↔ RADIANES
        double radianes = Math.toRadians(180); // π radianes
        double grados = Math.toDegrees(Math.PI); // 180°
        
        // FUNCIONES HIPERBÓLICAS
        double sinh = Math.sinh(1);            // seno hiperbólico
        double cosh = Math.cosh(1);            // coseno hiperbólico
        double tanh = Math.tanh(1);            // tangente hiperbólica
        
        // FUNCIONES INVERSAS
        double arcoseno = Math.asin(0.5);      // arcoseno (devuelve radianes)
        double arcocoseno = Math.acos(0.5);    // arcocoseno
        double arcotangente = Math.atan(1);    // arcotangente
        double atan2 = Math.atan2(1, 1);       // arcotangente de y/x
        
        // NÚMEROS ALEATORIOS
        double random1 = Math.random();        // [0.0, 1.0)
        int random2 = (int)(Math.random() * 100); // [0, 99]
        int random3 = (int)(Math.random() * 100) + 1; // [1, 100]
        
        // SEÑALES Y MISCELÁNEOS
        double signo = Math.signum(-3.14);     // -1.0 (signo)
        double ulp = Math.ulp(1.0);            // Unit in the Last Place
        double copiaSigno = Math.copySign(3.14, -1); // -3.14 (copia signo)
        
        // MÉTODOS DE PRECISIÓN
        double nextUp = Math.nextUp(1.0);      // Siguiente número hacia +infinito
        double nextDown = Math.nextDown(1.0);  // Siguiente número hacia -infinito
        
        System.out.println("PI: " + pi);
        System.out.println("2^3: " + potencia);
        System.out.println("√16: " + raizCuadrada);
        System.out.println("sen(45°): " + seno);
        System.out.println("Número aleatorio: " + random2);
        
        // EJEMPLOS PRÁCTICOS
        System.out.println("\n--- EJEMPLOS PRÁCTICOS ---");
        
        // Calcular hipotenusa
        double catetoA = 3, catetoB = 4;
        double hipotenusa = Math.sqrt(Math.pow(catetoA, 2) + Math.pow(catetoB, 2));
        System.out.println("Hipotenusa triángulo 3-4: " + hipotenusa);
        
        // Distancia entre dos puntos
        double x1 = 0, y1 = 0, x2 = 3, y2 = 4;
        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("Distancia puntos (0,0) a (3,4): " + distancia);
        
        // Área círculo
        double radio = 5;
        double areaCirculo = Math.PI * Math.pow(radio, 2);
        System.out.println("Área círculo radio 5: " + areaCirculo);
    }
}

/*
MÉTODOS PRINCIPALES MATH:
- Math.PI, Math.E → Constantes
- round(), floor(), ceil() → Redondeo
- abs() → Valor absoluto
- max(), min() → Comparación
- pow(), sqrt(), cbrt() → Potencias y raíces
- sin(), cos(), tan() → Trigonometría
- toRadians(), toDegrees() → Conversión
- random() → Números aleatorios
- log(), exp() → Logaritmos y exponencial

CARACTERÍSTICAS:
- Todos los métodos son static (se usan con Math.metodo())
- No necesita instanciación
- Precisión double para la mayoría de métodos
- Thread-safe (seguro para hilos)
*/