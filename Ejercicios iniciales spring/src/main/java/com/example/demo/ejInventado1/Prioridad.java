package com.example.demo.ejInventado1;
/*
 * EXPLICACION:
 * al empezar el programa, java creará esto automáticamente:
            Prioridad ROJO = new Prioridad("Prioridad alta");
            Prioridad VERDE = new Prioridad("Prioridad alta");  
            Prioridad AZUL = new Prioridad("Prioridad alta");
    ya tenemos los 3 objetos del enumlistos para usar por lo tanto tenemos que poner el construcor 
    privadoo para que no se puedan crear otros que no estén en el enum por defecto
    
 */
public enum Prioridad {
    ALTA("Prioridad alta"),
    MEDIA("Prioridad media"),
    BAJA("Prioridad baja");

    private final String textoPrioridad;

    //teiene que ser privado(ya que no podemos crear nuevas instancias de enum, hay las que hay)
    private Prioridad(String textoPrioridad){
        this.textoPrioridad=textoPrioridad;
    }

    //devuelve el texto
    public String getPrioridad(){
        return this.textoPrioridad;
    }

    //esto no puede existir porque sino podriamos cambiarle el mensaje a los enum 
    // public void setPrioridad(String textoPrioridad){
    //     this.textoPrioridad=textoPrioridad;
    // }
}
