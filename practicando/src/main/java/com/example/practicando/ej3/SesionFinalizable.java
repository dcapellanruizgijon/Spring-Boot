package com.example.practicando.ej3;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface SesionFinalizable {
    void cerrarSesion(HttpSession ses, HttpServletResponse resp);//abstracto para que en el controlador podamos hacer mas cosas
}
