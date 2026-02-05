package com.example.demo.controlador;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repositorios.RutinaRepository;
import com.example.demo.Servicios.EjercicioServicio;
import com.example.demo.Servicios.RutinaServicio;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.clases.*;
// import clases.Rutina;
// import clases.Usuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRutinas {

    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private RutinaServicio servicioRutina;
    @Autowired 
    private EjercicioServicio servicioEjercicio;
    
    // Página principal - si no hay usuario va al login
    @GetMapping("/")
    public String home(Model model, HttpSession session, @CookieValue(value = "ultimaRutina", defaultValue = "") String ultimaRutina) {
    														//busca la cookie llamada ultima rutina y pide su valor, si no la encuentra le da un valor por defecto (defaultvalue)
    	
        // Obtener usuario de la sesión(la sesion esta creada abajo en el post(/login)
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        // Si no hay usuario en sesión, redirigir al login
        if (usuario == null) {
            return "login";
        }
        
        //para controlar lo que pasa si aun no hubiera ultimaRutina(si el default value es empty("")
        if (ultimaRutina.isEmpty()) {
            ultimaRutina = "Nombre de la rutina no está disponible";
        }else{
            //para manejar el utlimo usuario que creo alguna rutina
            String[] usuarioRutina =ultimaRutina.split("-");
            // for (String s : usuarioRutina) {
            //     System.out.println("DATOOOOOO:"+s);
            // }
            ultimaRutina="La ultima rutina creada por alguien en la aplicación se llamó: "+usuarioRutina[1]+" y fue creada por el usuario: "+usuarioRutina[0];
        }


        //IMPORTANTE NO INCLUIRLO EN EL IF NI EL ELSE
        model.addAttribute("ultimaRutina", ultimaRutina);
        // Si hay usuario actual, pasar datos al modelo y mostrar la pagina index
        model.addAttribute("usuario", usuario);

        return "index";
    }
    
    
    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        
        if (logout != null) {
            model.addAttribute("message", "Has cerrado sesión correctamente");
        }
        
        return "login";
    }
        
    //Comentado porque ahora lo hace spring security
    // // Procesar el formulario de login
    // @PostMapping("/login")
    // public String procesarLogin(HttpSession session, Model model, @RequestParam String nom, @RequestParam String cont) {
    //     List<Usuario> usuarios=servicioUsuario.listarUsuarios();
    //     // Buscar usuario en el array
        
    //     for (Usuario usuario : usuarios) {

    //         if (usuario.getNombre().equals(nom) && usuario.getContrasena().equals(cont)) {

    //             //para que se carguen tambien las rutinas de la bbdd(hibernate sabe que rutinas corresponden a cada usuario gracias a las claves)
    //             //SIN HACER ESTO NO FUNCIONA YA QUE NO RESCATARIA LAS RUTINAS DE LA BBDD
    //             Hibernate.initialize(usuario.getRutinas());
    //             // También inicializar los ejercicios de cada rutina
    //             if (usuario.getRutinas() != null) {
    //                 for (Rutina rutina : usuario.getRutinas()) {
    //                     Hibernate.initialize(rutina.getEjercicios());
    //                 }
    //             }

    //             // Usuario encontrado - guardar en sesión
    //             session.setAttribute("usuario", usuario);
    //             return "redirect:/";  // Redirigir a la página principal
    //         }
    //     }   

    //     // Si no encuentra usuario, mostrar error
    //     model.addAttribute("error", "Usuario o contraseña incorrectos");
    //     return "login";
    // }

    @GetMapping("/registro")
    public String redirigeARegistro(){
        return "registro";
    }

    @PostMapping("/registro")
    public String nuevoRegistro(@RequestParam String nom, @RequestParam String cont, @RequestParam String edad, @RequestParam String peso, @RequestParam String altura){
        //asi se parsea un string
        int ed=Integer.parseInt(edad);
        double pes=Double.parseDouble(peso);
        double alt=Double.parseDouble(altura);
        System.out.println("edad: "+ed);
        System.out.println("peso: "+pes);
        System.out.println("altura: "+alt);
        Usuario u=new Usuario(nom, cont, ed, pes, alt);

        //sesion.setAttribute("usuario", u);
        servicioUsuario.guardarTrabajador(u);//lo guarda con la contraseña encriptada (metodo creado en el usuarioServicio)
        return "redirect:/login?registro=true";//redirigimos al login
    }

    //COmentado porque lo hce spring security
    // Cerrar sesión
    // @GetMapping("/cerrarSesion")
    // public String cerrarSesion(HttpSession session) {
    //     // Eliminar usuario de la sesión
    //     session.removeAttribute("usuario");
    //     return "redirect:/login";  // Redirigir al login
    // }
    
    

    // Crear rutina - Establece cookie
    @PostMapping("/crear-rutina")												//estos es un objeto y gracias a el podemos enviar la cookie al navegador DIFERENCIA CON MODEL ABAJO
    public String crearRutina(@RequestParam String nombre, HttpSession session, HttpServletResponse response) {
        
    	//obtenemos el usuario de la sesion
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        
      //si hay un usuario le añadimos la rutina al usuario
        if (usuario != null) {
        	Rutina r=new Rutina(nombre, usuario);
            usuario.agregarRutina(r);
            
            //como esta va a pasar a ser la ultima rutina la vamos a guardar en una cookie para mantenerla
            //las cookies no pueden tener  espacios ni , asi que le he puesto un "-"
            String usuarioYrutina=usuario.getNombre()+"-"+r.getNombre();
            System.out.println("usuario y rutina:"+usuarioYrutina);
            Cookie cookie = new Cookie("ultimaRutina", usuarioYrutina);
            cookie.setMaxAge(3 * 24 * 3600);
            
            //enviamos la cookie al servidor para que sea almacenada(gracias al objeto HttpServletResponse)
            //IMPORTANTE: httpServletResponse es un objeto que lleva contenido al cliente (como el model) IMPPORTANTE: La diferencia es que model se usa para thymeleaf(cosas que van a la plantilla html) y el httpServletResponse se usa en cookies para configurar la respuesta misma
            response.addCookie(cookie);

            

            //GUARDAMOS LA RUTINA EN LA BBDD
            servicioRutina.guardarRutina(r);
        }
        
        return "redirect:/";
    }

    @PostMapping("/eliminarRutina")
    public String eliminaRutina(@RequestParam int rutinaId, @RequestParam int rutinaIndex, HttpSession sesion){
        System.out.println("Indice de la rutina: "+rutinaId);
        Usuario u=(Usuario) sesion.getAttribute("usuario");

        servicioRutina.borrarRutina(rutinaId);
        u.getRutinas().remove(rutinaIndex);

        return "redirect:/";
    }
    

    // Agregar ejercicio a rutina
    @PostMapping("/agregar-ejercicio")
    public String agregarEjercicio(@RequestParam String nombreEjercicio, @RequestParam int rutinaIndex,  HttpSession session) {
        
    	//obtenemos el usuario de la sesion
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        	//agrega un ejercicio a una rutina especifica usando el indice(enviado desde el html)
        if (usuario != null && rutinaIndex >= 0 && rutinaIndex < usuario.getRutinas().size()) {
        	
        		//creamos una rutina y le damos el valor de getRutinas que es un array de todas las rutinas Y USAMOS .GET(RUTINAinDEX) PARA DECIRLE DE ESE ARRAY QUE RUTINA ES LA QUE BUSCO
            Rutina rutina = usuario.getRutinas().get(rutinaIndex);

            //agregamos el ejercicio (dandole el nombre que nos viene desde el formulario de /agregar-ejercicio
            Ejercicio ej=new Ejercicio(nombreEjercicio, rutina);
            servicioEjercicio.guardarEjercicio(ej);//guardamos el ejercicio en la bbdd
            //MUY IMPORTANTE: al hacer esto, JPA automaticamente actualiza el objeto para que podamos acceder tambien a su id :
        
            rutina.agregarEjercicio(ej);//guardamos el ejercicio en el array de ejercicios de la clase rutina

            
        }
        return "redirect:/";
    }

    @PostMapping("/cambiarEstado")
    public String cambiarEstadoEjercicio(@RequestParam Integer idEjercicio, HttpSession sesion){

        Ejercicio ejercicio=servicioEjercicio.traerEjercicio(idEjercicio);
        ejercicio.setCompletado();
        System.out.println("Ejercicio completado?:"+ejercicio.isCompletado());
        servicioEjercicio.guardarEjercicio(ejercicio);

        //actualizamos el usuario de la sesion (ES MEJOR HACERLO AQUI EN VEZ DE EN POSTMAPPING DE"/" YA QUE ASI SOLO REFRESCAMOS CUANDO HAY UN CAMBIO EN VEZ DE SIEMPRE QUE SE RECARGUE LA PÁGINA):
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        if (usuario != null) {
            // Traer usuario fresco desde BD
            Usuario usuarioActualizado = servicioUsuario.traerUsuario(usuario.getId());

            // Inicializamos las rutinas y los ejercicios en hibernate
            Hibernate.initialize(usuarioActualizado.getRutinas());
            for (Rutina rutina : usuarioActualizado.getRutinas()) {
                Hibernate.initialize(rutina.getEjercicios());
            }
            // Actualizar sesión
            sesion.setAttribute("usuario", usuarioActualizado);
        }
        return "redirect:/";
    }

    // Eliminar cookie de última rutina si pulsamos en el boton del formulario de eliminarla
    @GetMapping("/eliminar-cookie")
    public String eliminarCookie(HttpServletResponse response) {
    	
    	//para eliminar una cookie creamos tra con el mismo nombre que la que queremos eliminar
        Cookie cookie = new Cookie("ultimaRutina", null);//valor null pero no importa (se va a eliminar)
        cookie.setMaxAge(0); //Eliminamos la cookie poniendo su tiempo de vida a 0
        response.addCookie(cookie);
        return "redirect:/";
    }

    

}