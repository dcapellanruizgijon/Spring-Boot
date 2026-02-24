package com.example.demo.controlador;

import java.io.IOException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Servicios.EjercicioServicio;
import com.example.demo.Servicios.RutinaServicio;
import com.example.demo.Servicios.UsuarioServicio;
import com.example.demo.ServiciosImplementacion.ChatService;
import com.example.demo.ServiciosImplementacion.CloudinaryServicio;
import com.example.demo.clases.Ejercicio;
import com.example.demo.clases.Rutina;
import com.example.demo.clases.Usuario;

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

    /* para open ai */
    @Autowired
    private ChatService chatService;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    // Página principal - si no hay usuario va al login
    @GetMapping("/")
    public String home(Model model, HttpSession session,
            @CookieValue(value = "ultimaRutina", defaultValue = "") String ultimaRutina) {
        // busca la cookie llamada ultima rutina y pide su valor, si no la encuentra le
        // da un valor por defecto (defaultvalue)

        // Obtener usuario de la sesión(la sesion esta creada abajo en el post(/login)
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Si no hay usuario en sesión, redirigir al login
        if (usuario == null) {
            return "login";
        }

        // para controlar lo que pasa si aun no hubiera ultimaRutina(si el default value
        // es empty("")
        if (ultimaRutina.isEmpty()) {
            ultimaRutina = "Nombre de la rutina no está disponible";
        } else {
            // para manejar el utlimo usuario que creo alguna rutina
            String[] usuarioRutina = ultimaRutina.split("-");
            // for (String s : usuarioRutina) {
            // System.out.println("DATOOOOOO:"+s);
            // }
            ultimaRutina = "La ultima rutina creada por alguien en la aplicación se llamó: " + usuarioRutina[1]
                    + " y fue creada por el usuario: " + usuarioRutina[0];
        }

        // IMPORTANTE NO INCLUIRLO EN EL IF NI EL ELSE
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

    // Comentado porque ahora lo hace spring security
    // // Procesar el formulario de login
    // @PostMapping("/login")
    // public String procesarLogin(HttpSession session, Model model, @RequestParam
    // String nom, @RequestParam String cont) {
    // List<Usuario> usuarios=servicioUsuario.listarUsuarios();
    // // Buscar usuario en el array

    // for (Usuario usuario : usuarios) {

    // if (usuario.getNombre().equals(nom) && usuario.getContrasena().equals(cont))
    // {

    // //para que se carguen tambien las rutinas de la bbdd(hibernate sabe que
    // rutinas corresponden a cada usuario gracias a las claves)
    // //SIN HACER ESTO NO FUNCIONA YA QUE NO RESCATARIA LAS RUTINAS DE LA BBDD
    // Hibernate.initialize(usuario.getRutinas());
    // // También inicializar los ejercicios de cada rutina
    // if (usuario.getRutinas() != null) {
    // for (Rutina rutina : usuario.getRutinas()) {
    // Hibernate.initialize(rutina.getEjercicios());
    // }
    // }

    // // Usuario encontrado - guardar en sesión
    // session.setAttribute("usuario", usuario);
    // return "redirect:/"; // Redirigir a la página principal
    // }
    // }

    // // Si no encuentra usuario, mostrar error
    // model.addAttribute("error", "Usuario o contraseña incorrectos");
    // return "login";
    // }

    @GetMapping("/registro")
    public String redirigeARegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String nuevoRegistro(@RequestParam String nom, @RequestParam String cont,
            @RequestParam String edad, @RequestParam String peso,
            @RequestParam String altura) {

        try {
            int ed = Integer.parseInt(edad);
            double pes = Double.parseDouble(peso);
            double alt = Double.parseDouble(altura);

            Usuario u = new Usuario(nom, cont, ed, pes, alt);

            // Mejora 1: Manejar null explícitamente
            Usuario usuarioExistente = servicioUsuario.buscarPorNombre(u.getNombre());

            if (usuarioExistente != null) {
                return "redirect:/login?registro=false&error=usuario_existe";
            } else {
                servicioUsuario.guardarTrabajador(u);
                return "redirect:/login?registro=true";
            }

        } catch (NumberFormatException e) {
            // Mejora 2: Manejar errores de parseo
            return "redirect:/registro?error=formato_invalido";
        }
    }

    // COmentado porque lo hce spring security
    // Cerrar sesión
    // @GetMapping("/cerrarSesion")
    // public String cerrarSesion(HttpSession session) {
    // // Eliminar usuario de la sesión
    // session.removeAttribute("usuario");
    // return "redirect:/login"; // Redirigir al login
    // }

    // Crear rutina - Establece cookie
    @PostMapping("/crear-rutina") // estos es un objeto y gracias a el podemos enviar la cookie al navegador
                                  // DIFERENCIA CON MODEL ABAJO
    public String crearRutina(@RequestParam String nombre, HttpSession session, HttpServletResponse response) {

        // obtenemos el usuario de la sesion
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // si hay un usuario le añadimos la rutina al usuario
        if (usuario != null) {
            Rutina r = new Rutina(nombre, usuario);
            usuario.agregarRutina(r);

            // como esta va a pasar a ser la ultima rutina la vamos a guardar en una cookie
            // para mantenerla
            // las cookies no pueden tener espacios ni , asi que le he puesto un "-"
            String usuarioYrutina = usuario.getNombre() + "-" + r.getNombre();
            System.out.println("usuario y rutina:" + usuarioYrutina);
            Cookie cookie = new Cookie("ultimaRutina", usuarioYrutina);
            cookie.setMaxAge(3 * 24 * 3600);
            cookie.setPath("/");
            cookie.setHttpOnly(false);

            // enviamos la cookie al servidor para que sea almacenada(gracias al objeto
            // HttpServletResponse)
            // IMPORTANTE: httpServletResponse es un objeto que lleva contenido al cliente
            // (como el model) IMPPORTANTE: La diferencia es que model se usa para
            // thymeleaf(cosas que van a la plantilla html) y el httpServletResponse se usa
            // en cookies para configurar la respuesta misma
            response.addCookie(cookie);

            // GUARDAMOS LA RUTINA EN LA BBDD
            servicioRutina.guardarRutina(r);
        }

        return "redirect:/";
    }

    @PostMapping("/eliminarRutina")
    public String eliminaRutina(@RequestParam int rutinaId, @RequestParam int rutinaIndex, HttpSession sesion) {
        System.out.println("Indice de la rutina: " + rutinaId);
        Usuario u = (Usuario) sesion.getAttribute("usuario");

        servicioRutina.borrarRutina(rutinaId);
        u.getRutinas().remove(rutinaIndex);

        return "redirect:/";
    }

    // Agregar ejercicio a rutina
    @PostMapping("/agregar-ejercicio")
    public String agregarEjercicio(
            @RequestParam String nombreEjercicio,
            @RequestParam int rutinaIndex,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null && rutinaIndex >= 0 && rutinaIndex < usuario.getRutinas().size()) {

            // VALIDAR TAMAÑO 
            if (imagen != null && !imagen.isEmpty()) {
                long maxSize = 2 * 1024 * 1024; // 2MB maximo
                long fileSize = imagen.getSize();

                System.out
                        .println("📸 Tamaño de imagen: " + fileSize + " bytes (" + (fileSize / (1024 * 1024)) + "MB)");

                if (fileSize > maxSize) {
                    System.out.println("ERROR: Imagen demasiado grande");
                    redirectAttributes.addFlashAttribute("error", "La imagen no puede superar los 2MB (pesa " +
                            String.format("%.2f", (double) fileSize / (1024 * 1024)) + "MB)");
                    return "redirect:/";
                }
            }

            // Resto del código igual...
            try {
                Rutina rutina = usuario.getRutinas().get(rutinaIndex);
                Ejercicio ej = new Ejercicio(nombreEjercicio, rutina);

                if (imagen != null && !imagen.isEmpty()) {
                    String imagenUrl = cloudinaryServicio.subirImagen(imagen);
                    ej.setImagenUrl(imagenUrl);
                }

                servicioEjercicio.guardarEjercicio(ej);
                rutina.agregarEjercicio(ej);

                redirectAttributes.addFlashAttribute("exito", "Ejercicio agregado correctamente");

            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Error al agregar ejercicio: " + e.getMessage());
            }
        }

        return "redirect:/";
    }

    @PostMapping("/actualizar-imagen-ejercicio")
    public String actualizarImagenEjercicio(
            @RequestParam Integer ejercicioId,
            @RequestParam("imagen") MultipartFile imagen,
            HttpSession session,
            RedirectAttributes redirectAttributes) { // ← AÑADE RedirectAttributes

        System.out.println("\n🔍 === ACTUALIZAR IMAGEN ===");
        System.out.println("🆔 Ejercicio ID: " + ejercicioId);

        // 🔴 VALIDAR TAMAÑO MANUALMENTE
        if (imagen != null && !imagen.isEmpty()) {
            long maxSize = 2 * 1024 * 1024; // 2MB
            long fileSize = imagen.getSize();

            System.out.println("📸 Tamaño imagen: " + fileSize + " bytes (" + (fileSize / (1024 * 1024)) + "MB)");

            if (fileSize > maxSize) {
                double tamañoMB = (double) fileSize / (1024 * 1024);
                String mensaje = String.format("La imagen no puede superar los 2MB (pesa %.2fMB)", tamañoMB);
                System.out.println("❌ ERROR: " + mensaje);
                redirectAttributes.addFlashAttribute("error", mensaje);
                return "redirect:/";
            }

            // Validar tipo de archivo
            if (!imagen.getContentType().startsWith("image/")) {
                System.out.println("❌ ERROR: No es una imagen");
                redirectAttributes.addFlashAttribute("error", "El archivo debe ser una imagen (JPG, PNG, GIF)");
                return "redirect:/";
            }
        }

        Ejercicio ejercicio = servicioEjercicio.traerEjercicio(ejercicioId);

        if (ejercicio != null && imagen != null && !imagen.isEmpty()) {
            try {
                // Si ya tenía imagen, eliminar la anterior de Cloudinary
                if (ejercicio.getImagenUrl() != null) {
                    System.out.println("🗑️ Eliminando imagen anterior: " + ejercicio.getImagenUrl());
                    cloudinaryServicio.eliminarImagen(ejercicio.getImagenUrl());
                }

                // Subir nueva imagen
                System.out.println("⬆️ Subiendo nueva imagen a Cloudinary...");
                String nuevaUrl = cloudinaryServicio.subirImagen(imagen);
                ejercicio.setImagenUrl(nuevaUrl);
                servicioEjercicio.guardarEjercicio(ejercicio);
                System.out.println("✅ Imagen actualizada para ejercicio: " + ejercicio.getNombre());

                redirectAttributes.addFlashAttribute("exito", "Imagen actualizada correctamente");

                // Actualizar sesión
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                if (usuario != null) {
                    Usuario usuarioActualizado = servicioUsuario.traerUsuario(usuario.getId());
                    Hibernate.initialize(usuarioActualizado.getRutinas());
                    for (Rutina rutina : usuarioActualizado.getRutinas()) {
                        Hibernate.initialize(rutina.getEjercicios());
                    }
                    session.setAttribute("usuario", usuarioActualizado);
                }

            } catch (Exception e) {
                System.out.println("❌ Error al actualizar imagen: " + e.getMessage());
                redirectAttributes.addFlashAttribute("error", "Error al subir la imagen: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Ejercicio no encontrado o imagen vacía");
            redirectAttributes.addFlashAttribute("error", "No se pudo actualizar la imagen");
        }

        return "redirect:/";
    }

    @PostMapping("/cambiarEstado")
    public String cambiarEstadoEjercicio(@RequestParam Integer idEjercicio, HttpSession sesion) {
        Ejercicio ejercicio = servicioEjercicio.traerEjercicio(idEjercicio);
        ejercicio.setCompletado();
        servicioEjercicio.guardarEjercicio(ejercicio);

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        if (usuario != null) {
            Usuario usuarioActualizado = servicioUsuario.traerUsuarioSinCache(usuario.getId());

            // Inicializar relaciones
            Hibernate.initialize(usuarioActualizado.getRutinas());
            for (Rutina rutina : usuarioActualizado.getRutinas()) {
                Hibernate.initialize(rutina.getEjercicios());
            }

            sesion.setAttribute("usuario", usuarioActualizado);
        }
        return "redirect:/";
    }

    // Eliminar cookie de última rutina si pulsamos en el boton del formulario de
    // eliminarla
    @GetMapping("/eliminar-cookie")
    public String eliminarCookie(HttpServletResponse response) {

        // para eliminar una cookie creamos tra con el mismo nombre que la que queremos
        // eliminar
        Cookie cookie = new Cookie("ultimaRutina", null);// valor null pero no importa (se va a eliminar)
        cookie.setMaxAge(0); // Eliminamos la cookie poniendo su tiempo de vida a 0
        response.addCookie(cookie);
        return "redirect:/";
    }

    /*PARA MANEJAR CONSULTAS OPEN AI*/
    @GetMapping("/chat")
    public String mostrarChat(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        return "chat";
    }

    @PostMapping("/chat/enviar")
    public String enviarMensajeChat(@RequestParam String mensaje,
            HttpSession session,
            Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        String respuesta = chatService.generarRespuesta(mensaje);

        model.addAttribute("usuario", usuario);
        model.addAttribute("pregunta", mensaje);
        model.addAttribute("respuesta", respuesta);

        return "chat";
    }

    
}