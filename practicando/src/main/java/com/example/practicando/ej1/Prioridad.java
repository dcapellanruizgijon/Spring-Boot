package com.example.practicando.ej1;

public enum Prioridad {
    BAJA("Prioridad baja"),
    MEDIA("Prioridad intermedia"),
    ALTA("Prioridad alta");

    private final String txt;

    private Prioridad(String txt){
        this.txt=txt;
    }

    public String gettxt(){
        return this.txt;
    }
}
