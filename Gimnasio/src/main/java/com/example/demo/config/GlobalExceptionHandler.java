package com.example.demo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
        logger.info("✅ GlobalExceptionHandler inicializado");
    }

    // Manejador para cualquier excepción inesperada
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "Ocurrió un error inesperado");
        mav.addObject("detalle", ex.getMessage());
        return mav;
    }

    // Manejador de errores de base de datos
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDatabaseError() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "Error en la base de datos, intente más tarde");
        return mav;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handle404(NoResourceFoundException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "La página que buscas no existe (Error 404)");
        mav.addObject("icono", "🔍");
        mav.addObject("volver", "/");
        return mav;
    }

}