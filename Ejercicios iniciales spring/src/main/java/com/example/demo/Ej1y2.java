package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Ej1y2 {
	
	// EJERCICIO 1
	
	@GetMapping("/nuevoAnuncio") // petición de dirreccion web o desde el formulario
	public String metodo() {
		return "nuevoAnuncio"; // ¿A qué HTML me voy?
	}
	
	@GetMapping("/anuncio") // petición de dirreccion web o desde el formulario
	public String metodo(@RequestParam("nombre") String nombre, @RequestParam("asunto") String asunto, @RequestParam("comentario") String comentario, Model model) {
		model.addAttribute("nombre", nombre); // le digo a model que lleve a "nombre"
		model.addAttribute("asunto", asunto); // le digo a model que lleve a "asunto"
		model.addAttribute("comentario", comentario); // le digo a model que lleve a "comentario"
		return "anuncio"; // ¿A qué HTML me voy?
	}
	
	
	// EJERCICIO 2
	
	// Contacto es una clase que he creado anteriormente
	// tiene un atributo Sexo que también he creado yo como enum,
	// no hace falta hacerlo así, se puede hacer con un
	// boolean por ejemplo.
	static ArrayList<Contacto> contactos = new ArrayList<>();
	
	static LocalDate fecha = LocalDate.now();
	
	static {		
		contactos.add(new Contacto("Juan", "Perez", "jp@gmail.com", Sexo.Hombre));
		contactos.add(new Contacto("Maria", "Lopez", "ml@gmail.com", Sexo.Mujer));
		contactos.add(new Contacto("Beto", "Luna", "bluna@gmail.com", Sexo.Hombre));
	}
	
	@GetMapping("/Ejercicio2")
	public String ejercicio2(Model model) {
		
		model.addAttribute("fecha", fecha);
		// Mando el arraylist de contactos, para recorrerlo con un foreach en thymeleaf
		model.addAttribute("arraylist", contactos);
		return "Ejercicio2";
	}
}
