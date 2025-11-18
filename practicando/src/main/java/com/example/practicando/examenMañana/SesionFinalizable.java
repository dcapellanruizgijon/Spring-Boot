package com.example.practicando.examenMañana;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface SesionFinalizable {
    void cerrarSesion(HttpSession ses, HttpServletResponse resp);//abstracto para que en el controlador podamos hacer mas cosas
}
