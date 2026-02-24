package com.example.demo.config;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView mav = new ModelAndView("error");//Para que el error se muestre en la vista error.html
        mav.addObject("mensaje", "Ocurrió un error inesperado");
        mav.addObject("detalle", ex.getMessage());
        return mav;
    }

//si la imagen es de mas de 1 mb entra por aquí ya que es mas específico que el de arriba
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "La imagen es demasiado grande (máx 1MB)");
        return mav;
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDatabaseError() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("mensaje", "Error en la base de datos, intente mas tarde");
        return mav;
    }
    
}