package com.example.demo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.servlet.ModelAndView;


/*Para manejar errores imprevisitos */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    // Manejador para cualquier excepción inesperada
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView mav = new ModelAndView("error");//PAGINA HTML A LA QUE SE DIRIGIRÁ AL OCURRIR EL ERROR
        mav.addObject("mensaje", "Ocurrió un error inesperado");
        mav.addObject("detalle", ex.getMessage());
        return mav;
    }

    // Manejador de errores de base de datos
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDatabaseError() {
        ModelAndView mav = new ModelAndView("error");//PAGINA HTML A LA QUE SE DIRIGIRÁ AL OCURRIR EL ERROR
        mav.addObject("mensaje", "Error en la base de datos, intente más tarde");
        return mav;
    }

    //maneja el error 404
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handle404(NoResourceFoundException ex) {
        ModelAndView mav = new ModelAndView("error");//PAGINA HTML A LA QUE SE DIRIGIRÁ AL OCURRIR EL ERROR
        mav.addObject("mensaje", "La página que buscas no existe (Error 404)");
        mav.addObject("icono", "🔍");
        mav.addObject("volver", "/");
        return mav;
    }

}