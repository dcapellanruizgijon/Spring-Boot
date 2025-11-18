package ej4;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class controlador {
	
	private ArrayList<anuncio> anuncios= new ArrayList<>();
	
	//esto serior lo mismo que ("/index") es solo una convencion
	@GetMapping("/")
	public String redir(HttpSession sesion, Model model) {
		
		if(sesion.getAttribute("primeraVez")==null) {
			model.addAttribute("mensajeBienvenida", "BIENVENIDO");//si es la primera vez lo pasa como parametro, si no lo es no lo pasaria y no funcionaria el if del index
			sesion.setAttribute("primeraVez", "Ya no es la primera vez");//para que la proxima vez no entre en el if
		}
		
		model.addAttribute("anuncios", anuncios);//la primera vez estará vacio
		
		return "ej4/index";
	}
	
	@GetMapping("/dirigeNuevoAnuncio")
	public String irFormulario() {
		return "ej4/nuevoAnuncio";
	}
	
	@PostMapping("/nuevoAnuncio")
	public String redireccion(HttpSession sesion, Model model,@RequestParam String nom, @RequestParam String asunt, @RequestParam String desc) {
		anuncio anuncio=new anuncio(nom, asunt, desc);
		
		anuncios.add(anuncio);
		
		//guradamos en la sesion el nombre del ultimo anuncio
		sesion.setAttribute("nombreAnuncio", anuncio.getNombre());
		model.addAttribute(anuncios);
		
		return "redirect:/";//a que html voy?
	}
	
	@GetMapping("/mostrarAnuncio")
	public String redireccio(HttpSession sesion, Model model, @RequestParam String nombre) {
		
		for(anuncio a : anuncios){
			if(a.getNombre().equals(nombre)){
				model.addAttribute("nombre", a.getNombre());
				model.addAttribute("asunto", a.getAsunto());
				model.addAttribute("descripcion", a.getDescripcion());
			}
		}

		return "ej4/anuncio";

	}

	@GetMapping("/index")
	public String getMethodName() {
		return "redirect:/";
	}
	
	
}
