package com.example.practicando.ej3;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ExControlador implements SesionFinalizable{

    @GetMapping("/")
    public String redirigir() {
        return "ej3/login";//a que html voy?
        
    }
    
    
    @PostMapping("/login")//esto es el formulario
    public String login(HttpSession sesion, @RequestParam String nom, Model model) {

        //si es uno de estos nombres lo guarda en la sesion
        if(nom.equals("Cervantes") || nom.equals("Lorca") || nom.equals("Zafón")){
            sesion.setAttribute("autor", nom);//lo metemos en una sesion para poder acceder desde /libros
            
            //creamos el gestor al logearnos ya que el examen dice que al reiniciar la sesion los libros y la tabla de autores se reinicia
            sesion.setAttribute("gestor", new GestorLibros());
            return "redirect:/libros";
        }else{
            model.addAttribute("mensaje", "Error, con este nobre no puede entrar");
            return "ej3/login";//directamente para no perder el model
        }
    }
    


    @GetMapping("/libros")
    public String muestraLibros(HttpSession sesion, Model mod) {
        //si aun no hemos creado el gestor vamos a que lo cree
        if(sesion.getAttribute("gestor")==null){
            
            return "redirect:/";
        }
            //sacamos el gestor y el autor de la sesion
            GestorLibros gestor=(GestorLibros) sesion.getAttribute("gestor");
            String autor=(String) sesion.getAttribute("autor");//hay que parsearlo porque sesion es un objeto
            mod.addAttribute("nombreAutor", autor);

        String isbnError=(String)sesion.getAttribute("isbnError");
        mod.addAttribute("isbnError", isbnError);
        sesion.removeAttribute("isbnError");

        if(gestor.getLibros().isEmpty()) {
            mod.addAttribute("mensaje", "Ahora mismo no hay libros");
            return"ej3/index";
        }
            mod.addAttribute("arrayLibros", gestor.getLibros());//arrayList con los libros
            mod.addAttribute("autoresContrasena", gestor.getAutorContraseña());//arrayAsociativo(hashmap)
            mod.addAttribute("cumpleCondicion", gestor.cumpleCondicion());//verdadero o falso
            
            
            return "ej3/index";
    }



    @PostMapping("/nuevo")//formulario de libros
    public String formularioNuevoLibro(Model mod, HttpSession sesion, @RequestParam String titulo, @RequestParam String autor, @RequestParam int precio, @RequestParam(required = false) Integer isbn) {//required = false le dice a Spring: "El parámetro es opcional, si no viene, no pasa nada, le asigno valor null". int es un tipo primitivo que NO puede ser null por eso uso Integer
        
        GestorLibros gestor=(GestorLibros) sesion.getAttribute("gestor");

        //VALIDACIONES
        if(titulo==null || titulo.equals("")){
            return "redirect:/libros";
        }
        
        //PRIMERO VER SI ES NULL ANTES DE PASARLO A LA FUNCION
        if (isbn==null || gestor.esISBNRepetido(isbn)) {
            sesion.setAttribute("isbnError", "El isbn insertado no ha sido valido");
            return "redirect:/libros";
        }else{
            isbn=(int) isbn;
        }

        //si el autor viene vacío, por defecto le damos el nombre del autor que tenga la sesion iniciada (despues de ver si el isbn es valido para que si no lo es no guarde el autor)
        if(autor==null || autor.equals("")){
            autor=(String)sesion.getAttribute("autor");
        }else{
            //si es un autor nuevo lo añadimos al hashmap de autores
            String contraseñaAutor=gestor.crearContrasena(autor);
            HashMap<String, String> al = gestor.getAutorContraseña();
            al.put(autor, contraseñaAutor);
        }

            Libro l=new Libro(titulo, autor, precio, isbn);
            gestor.addLibro(l);
        
        
        return "redirect:/libros";
    }
    

    @GetMapping("/cerrarSesion")
    public void cerrar(HttpSession sesion, HttpServletResponse resp) {
        cerrarSesion(sesion, resp);
    }
    
    @Override
    public void cerrarSesion(HttpSession sesion, HttpServletResponse resp) {
        sesion.invalidate();//elimina todas las sesiones

        //Si no usamos try esto da error ya que, puede fallar porque como es una respuesta HTTP java obliga a manejarlo
        try{
            resp.sendRedirect("/");//redirige al login
        }catch(Exception e){
            e.printStackTrace();//maneja errores si los hay
        }
        
    }

    
}
