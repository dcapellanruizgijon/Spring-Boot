package com.example.demo.clases;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestBasico {
    
    private String mensaje;
    
    @Before
    public void setUp() {
        mensaje = "Hola Tests!";
        System.out.println("✅ Setup ejecutado");
    }
    
    @Test
    public void testQueSiemprePasa() {
        assertTrue(true);
        System.out.println("✅ Test 1 pasado");
    }
    
    @Test
    public void testCompararCadenas() {
        assertEquals("Hola Tests!", mensaje);
        System.out.println("✅ Test 2 pasado"); 
    }
    
    @Test
    public void testNumeros() {
        int resultado = 2 + 2;
        assertEquals(4, resultado);
        System.out.println("✅ Test 3 pasado");
    }
}