package com.ejemplo.controller;

import com.ejemplo.model.Carrito;
import com.ejemplo.model.Producto;
import com.ejemplo.model.Usuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/sesion")
public class SesionCookieController {
    
    // Lista de productos de ejemplo
    private final java.util.List<Producto> productos = Arrays.asList(
        new Producto(1L, "Laptop", "Laptop Gaming", 999.99, 10),
        new Producto(2L, "Mouse", "Mouse inalámbrico", 29.99, 50),
        new Producto(3L, "Teclado", "Teclado mecánico", 79.99, 25),
        new Producto(4L, "Monitor", "Monitor 24 pulgadas", 199.99, 15)
    );

    // =========================================================================
    // MANEJO DE SESIONES
    // =========================================================================
    
    @GetMapping("/login")
    public String mostrarLogin(Model model, HttpSession session) {
        // Si ya está logueado, redirigir al perfil
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/sesion/perfil";
        }
        return "sesion/login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) Boolean rememberMe,
            HttpSession session,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        
        // Validación simple (en una app real usarías Spring Security)
        if ("admin".equals(username) && "1234".equals(password)) {
            Usuario usuario = new Usuario(1L, "Administrador", "admin@ejemplo.com", 30);
            
            // Guardar en sesión
            session.setAttribute("usuarioLogueado", usuario);
            session.setAttribute("loginTime", java.time.LocalDateTime.now());
            
            // Crear carrito vacío
            session.setAttribute("carrito", new Carrito());
            
            // Cookie "remember me"
            if (rememberMe != null && rememberMe) {
                Cookie rememberCookie = new Cookie("rememberUser", username);
                rememberCookie.setMaxAge(30 * 24 * 60 * 60); // 30 días
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
            }
            
            redirectAttributes.addFlashAttribute("mensaje", "¡Bienvenido " + usuario.getNombre() + "!");
            return "redirect:/sesion/perfil";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/sesion/login";
        }
    }
    
    @GetMapping("/perfil")
    public String perfilUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (usuario == null) {
            return "redirect:/sesion/login";
        }
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("loginTime", session.getAttribute("loginTime"));
        model.addAttribute("sessionId", session.getId());
        
        return "sesion/perfil";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        // Eliminar cookie remember me
        Cookie cookie = new Cookie("rememberUser", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
        // Invalidar sesión
        session.invalidate();
        
        redirectAttributes.addFlashAttribute("mensaje", "Sesión cerrada exitosamente");
        return "redirect:/sesion/login";
    }

    // =========================================================================
    // CARRITO DE COMPRAS CON SESIÓN
    // =========================================================================
    
    @GetMapping("/carrito")
    public String verCarrito(HttpSession session, Model model) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (usuario == null) {
            return "redirect:/sesion/login";
        }
        
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }
        
        model.addAttribute("carrito", carrito);
        model.addAttribute("productos", productos);
        model.addAttribute("usuario", usuario);
        
        return "sesion/carrito";
    }
    
    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(
            @RequestParam Long productoId,
            @RequestParam(defaultValue = "1") int cantidad,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/sesion/login";
        }
        
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }
        
        Optional<Producto> producto = productos.stream()
                .filter(p -> p.getId().equals(productoId))
                .findFirst();
        
        if (producto.isPresent()) {
            carrito.agregarProducto(producto.get(), cantidad);
            redirectAttributes.addFlashAttribute("mensaje", 
                "Producto '" + producto.get().getNombre() + "' agregado al carrito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
        }
        
        return "redirect:/sesion/carrito";
    }
    
    @PostMapping("/carrito/actualizar")
    public String actualizarCarrito(
            @RequestParam Long productoId,
            @RequestParam int cantidad,
            HttpSession session) {
        
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito != null) {
            carrito.actualizarCantidad(productoId, cantidad);
        }
        
        return "redirect:/sesion/carrito";
    }
    
    @PostMapping("/carrito/eliminar")
    public String eliminarDelCarrito(
            @RequestParam Long productoId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito != null) {
            carrito.eliminarProducto(productoId);
            redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado del carrito");
        }
        
        return "redirect:/sesion/carrito";
    }
    
    @PostMapping("/carrito/limpiar")
    public String limpiarCarrito(HttpSession session, RedirectAttributes redirectAttributes) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito != null) {
            carrito.limpiar();
            redirectAttributes.addFlashAttribute("mensaje", "Carrito limpiado");
        }
        
        return "redirect:/sesion/carrito";
    }

    // =========================================================================
    // MANEJO DE COOKIES
    // =========================================================================
    
    @GetMapping("/preferencias")
    public String preferencias(HttpServletRequest request, Model model) {
        // Leer cookies existentes
        String tema = getCookieValue(request, "tema", "claro");
        String idioma = getCookieValue(request, "idioma", "es");
        String itemsPorPagina = getCookieValue(request, "itemsPagina", "10");
        
        model.addAttribute("temaActual", tema);
        model.addAttribute("idiomaActual", idioma);
        model.addAttribute("itemsPorPaginaActual", itemsPorPagina);
        
        return "sesion/preferencias";
    }
    
    @PostMapping("/preferencias")
    public String guardarPreferencias(
            @RequestParam String tema,
            @RequestParam String idioma,
            @RequestParam String itemsPorPagina,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        
        // Guardar en cookies
        addCookie(response, "tema", tema, 365 * 24 * 60 * 60);
        addCookie(response, "idioma", idioma, 365 * 24 * 60 * 60);
        addCookie(response, "itemsPagina", itemsPorPagina, 365 * 24 * 60 * 60);
        
        redirectAttributes.addFlashAttribute("mensaje", "Preferencias guardadas correctamente");
        return "redirect:/sesion/preferencias";
    }
    
    @GetMapping("/contador")
    public String contadorVisitas(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Contador de visitas con cookie
        String contadorStr = getCookieValue(request, "contadorVisitas", "0");
        int contador;
        
        try {
            contador = Integer.parseInt(contadorStr);
        } catch (NumberFormatException e) {
            contador = 0;
        }
        
        contador++;
        
        // Guardar cookie actualizada
        addCookie(response, "contadorVisitas", String.valueOf(contador), 365 * 24 * 60 * 60);
        
        model.addAttribute("contador", contador);
        model.addAttribute("primeraVisita", contador == 1);
        
        return "sesion/contador";
    }

    // =========================================================================
    // MÉTODOS UTILITARIOS
    // =========================================================================
    
    private String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return defaultValue;
    }
    
    private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true); // Protección contra XSS
        response.addCookie(cookie);
    }
}