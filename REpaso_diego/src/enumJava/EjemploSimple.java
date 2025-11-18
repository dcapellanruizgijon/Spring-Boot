package enumJava;
public class EjemploSimple {
    
    // DEFINICIÓN DEL ENUM - como una clase especial
    public enum EstadoPedido {
        // Estas son las CONSTANTES del enum (siempre en mayúsculas)
        PENDIENTE,      // Valor 0 - Estado inicial del pedido
        CONFIRMADO,     // Valor 1 - Pedido confirmado por el sistema
        EN_PREPARACION, // Valor 2 - En proceso de preparación
        ENVIADO,        // Valor 3 - Pedido enviado al cliente
        ENTREGADO,      // Valor 4 - Pedido entregado exitosamente
        CANCELADO       // Valor 5 - Pedido cancelado
    }
    
    public static void main(String[] args) {
        
        // ============================================
        // 1. DECLARACIÓN Y ASIGNACIÓN
        // ============================================
        
        // Declarar variable de tipo EstadoPedido y asignarle un valor
        EstadoPedido miPedido = EstadoPedido.PENDIENTE;
        
        // También se puede declarar así
        EstadoPedido otroPedido = EstadoPedido.ENTREGADO;
        
        
        // ============================================
        // 2. USO BÁSICO - COMPARACIONES
        // ============================================
        
        // Comparación con == (RECOMENDADO para enums)
        if (miPedido == EstadoPedido.PENDIENTE) {
            System.out.println("El pedido está pendiente de procesar");
        }
        
        // Comparación con equals() (también funciona)
        if (otroPedido.equals(EstadoPedido.ENTREGADO)) {
            System.out.println("¡Pedido entregado con éxito!");
        }
        
        
        // ============================================
        // 3. SWITCH CON ENUM - MUY ÚTIL
        // ============================================
        
        System.out.println("\n--- PROCESANDO ESTADO DEL PEDIDO ---");
        
        switch (miPedido) {
            case PENDIENTE:
                System.out.println("Acción: Notificar al cliente");
                break;
                
            case CONFIRMADO:
                System.out.println("Acción: Pasar a preparación");
                break;
                
            case EN_PREPARACION:
                System.out.println("Acción: Asignar cocinero");
                break;
                
            case ENVIADO:
                System.out.println("Acción: Enviar tracking");
                break;
                
            case ENTREGADO:
                System.out.println("Acción: Cerrar pedido");
                break;
                
            case CANCELADO:
                System.out.println("Acción: Reembolsar pago");
                break;
        }
        
        
        // ============================================
        // 4. MÉTODOS ÚTILES DE LOS ENUMS
        // ============================================
        
        // name() - Devuelve el nombre de la constante como String
        String nombreEstado = miPedido.name();
        System.out.println("\nNombre del estado: " + nombreEstado); // "PENDIENTE"
        
        // ordinal() - Devuelve la posición (índice) en la declaración
        int posicion = miPedido.ordinal();
        System.out.println("Posición en el enum: " + posicion); // 0
        
        // values() - Devuelve un array con TODAS las constantes del enum
        System.out.println("\n--- TODOS LOS ESTADOS POSIBLES ---");
        EstadoPedido[] todosEstados = EstadoPedido.values();
        for (EstadoPedido estado : todosEstados) {
            System.out.println("- " + estado.name() + " (posición " + estado.ordinal() + ")");
        }
        
        // valueOf() - Convierte un String en el valor del enum
        String texto = "ENVIADO";
        EstadoPedido estadoDesdeTexto = EstadoPedido.valueOf(texto);
        System.out.println("\nEstado desde texto '" + texto + "': " + estadoDesdeTexto);
        
        
        // ============================================
        // 5. EJEMPLO PRÁCTICO - MÉTODO CON ENUM
        // ============================================
        
        System.out.println("\n--- SIMULACIÓN DE PROCESO ---");
        simularProcesoPedido();
    }
    
    /**
     * MÉTODO QUE DEMUESTRA EL USO PRÁCTICO DE ENUMS
     * Simula el flujo de estados de un pedido
     */
    public static void simularProcesoPedido() {
        
        // Pedido comienza en estado PENDIENTE
        EstadoPedido estadoActual = EstadoPedido.PENDIENTE;
        System.out.println("Estado inicial: " + estadoActual);
        
        // Simulamos el proceso del pedido
        estadoActual = EstadoPedido.CONFIRMADO;
        System.out.println("Pedido confirmado: " + estadoActual);
        
        estadoActual = EstadoPedido.EN_PREPARACION;
        System.out.println("En preparación: " + estadoActual);
        
        estadoActual = EstadoPedido.ENVIADO;
        System.out.println("Enviado al cliente: " + estadoActual);
        
        estadoActual = EstadoPedido.ENTREGADO;
        System.out.println("¡Pedido entregado!: " + estadoActual);
        
        // Verificamos si el pedido está completado
        if (esPedidoCompletado(estadoActual)) {
            System.out.println("✅ El pedido ha sido completado exitosamente");
        }
    }
    
    /**
     * MÉTODO QUE USA ENUM COMO PARÁMETRO
     * @param estado El estado del pedido a verificar
     * @return true si el pedido está completado (entregado o cancelado)
     */
    public static boolean esPedidoCompletado(EstadoPedido estado) {
        // Los enums son perfectos para este tipo de verificaciones
        return estado == EstadoPedido.ENTREGADO || estado == EstadoPedido.CANCELADO;
    }
}

/*
¿QUÉ VENTAJAS TIENE USAR ENUM EN ESTE EJEMPLO?

1. ✅ SEGURO: No puedo asignar un estado inválido
   EstadoPedido estado = "INEXISTENTE"; // ❌ ERROR de compilación

2. ✅ AUTODOCUMENTADO: Los estados posibles son claros
3. ✅ FÁCIL DE MANTENER: Si añado un estado, el compilador me avisa donde actualizar
4. ✅ EFICIENTE: Las comparaciones con == son rápidas
5. ✅ ORDENADO: Reemplaza "magic numbers" o strings mágicos

USOS COMUNES DE ENUMS:
- Estados de un proceso (como este ejemplo)
- Días de la semana, meses
- Tipos de usuario (ADMIN, USER, GUEST)
- Categorías de productos
- Niveles de prioridad (ALTA, MEDIA, BAJA)
- Opciones de configuración

REGLA IMPORTANTE:
Los enums son clases especiales que heredan de java.lang.Enum
Cada constante es una INSTANCIA ÚNICA (singleton) de la clase enum
*/