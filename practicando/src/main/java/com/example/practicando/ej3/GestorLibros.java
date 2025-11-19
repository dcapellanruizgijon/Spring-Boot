package com.example.practicando.ej3;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorLibros {

    private ArrayList<Libro> libros;
    private HashMap<String, String> autorContraseña;

    //sin parametros
    public GestorLibros(){
        //inicializamos
        this.libros=new ArrayList<Libro>();
        this.autorContraseña=new HashMap<>();

        //le damos las claves y el valor al hash map
        autorContraseña.put("Cervantes", crearContrasena("Cervantes"));
        autorContraseña.put("Lorca", crearContrasena("Lorca"));
        autorContraseña.put("Zafón", crearContrasena("Zafón"));
    }



    
    public ArrayList<Libro> getLibros() {
        return libros;
    }
    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    public HashMap<String, String> getAutorContraseña() {
        return autorContraseña;
    }
    public void setAutorContraseña(HashMap<String, String> autorContraseña) {
        this.autorContraseña = autorContraseña;
    }


    public void addLibro(Libro l){
        libros.add(l);
    }


    public boolean esISBNRepetido(int nuevoIsbn){
        boolean respuesta=false;
        for(Libro libro:libros){
            if(libro.getIsbn()==nuevoIsbn){
                respuesta=true;
            }
        }
        return respuesta;
    }

    public String crearContrasena(String nombre){
        String primeraLetra=nombre.substring(0, 1);
        String resto=String.valueOf(nombre.length());
        return primeraLetra+resto;
    }

    public boolean estaVacio(){
        if(libros.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public boolean cumpleCondicion(){
        int contador=0;
        for(Libro l : libros){
            if (l.getPrecio()>=10 && l.getTitulo().length()>=10) {
                contador++;
            }
        }

        if(contador>=5){
            return true;
        }else{
            return false;
        }
    }
}
