package com.example.demo.clases;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioTest {
    
    private Usuario usuario;
    
    @Before
    public void setUp() {
        usuario = new Usuario("test", "pass", 25, 70, 175);
        System.out.println(" Preparando test...");
    }
    
    @Test
    public void testCrearUsuario() {
        assertNotNull(usuario);
        assertEquals("test", usuario.getNombre());
        System.out.println("Test 1: Crear usuario funciona");
    }
    
    @Test
    public void testCalcularIMC() {
        double imc = usuario.calculaIndiceMasaCorporal();
        System.out.println("Test 2: IMC calculado = " + imc);
        assertEquals(22.86, imc, 0.01);
    }
    
    @Test
    public void testCategoriaIMC() {
        String categoria = usuario.obtenerCategoriaIMC();
        System.out.println("Test 3: Categoría = " + categoria);
        assertEquals("Normal", categoria);
    }
    
    @Test
    public void testRolAdmin() {
        assertFalse(usuario.isAdmin());
        usuario.setRol("ADMIN");
        assertTrue(usuario.isAdmin());
        System.out.println("Test 4: Rol ADMIN funciona");
    }
}
