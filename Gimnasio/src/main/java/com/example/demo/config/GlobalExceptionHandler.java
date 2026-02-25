package com.example.demo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/*Para manejar errores imprevisitos */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    // Manejador para cualquier excepción inesperada
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex, HttpServletRequest request) {

        // NO INTERCEPTA PETICIONES DE SWAGGER
        String path = request.getRequestURI();
        if (path.contains("/swagger") || path.contains("/v3/api-docs") || path.contains("/api-docs")) {
            // Deja que Spring maneje estas excepciones normalmente
            throw new RuntimeException(ex);
        }

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "Ocurrió un error inesperado");
        mav.addObject("detalle", ex.getMessage());
        return mav;
    }

    // Manejador de errores de base de datos
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDatabaseError(HttpServletRequest request) {
        String path = request.getRequestURI();
        if (path.contains("/swagger") || path.contains("/v3/api-docs")) {
            throw new RuntimeException();
        }

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "Error en la base de datos, intente más tarde");
        return mav;
    }

    // Manejador de 404
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ModelAndView handle404(org.springframework.web.servlet.resource.NoResourceFoundException ex,
            HttpServletRequest request) {

        String path = request.getRequestURI();
        // Si es una petición de Swagger, no la interceptes
        if (path.contains("/swagger") || path.contains("/v3/api-docs") || path.contains("/api-docs")) {
            throw new RuntimeException(ex);
        }

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "La página que buscas no existe (Error 404)");
        mav.addObject("icono", "🔍");
        mav.addObject("volver", "/");
        return mav;
    }
}