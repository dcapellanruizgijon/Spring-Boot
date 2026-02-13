package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Servicios.EjercicioServicio;
import com.example.demo.Servicios.RutinaServicio;
import com.example.demo.clases.Ejercicio;
import com.example.demo.clases.Rutina;

@RestController  // IMPORTANTE esto es para API REST
@RequestMapping("/api")
public class ApiRutinasController {
    
    @Autowired
    private RutinaServicio servicioRutina;
    @Autowired
    private EjercicioServicio servicioEjercicio;

    // GET => todas las rutinas
    @GetMapping
    public List<Rutina> listarRutinas() {
        return servicioRutina.listarTodasLasRutinas();
    }

    // GET => rutina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rutina> obtenerRutina(@PathVariable int id) {
        Rutina rutina = servicioRutina.getRutinaById(id);
        if (rutina != null) {
            return new ResponseEntity<>(rutina, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    // GET => ejercicios de una rutina
    @GetMapping("/{id}/ejercicios")
    public ResponseEntity<List<Ejercicio>> listarEjercicios(@PathVariable int id) {
        Rutina rutina = servicioRutina.getRutinaById(id);
        if (rutina != null) {
            return new ResponseEntity<>(rutina.getEjercicios(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // DELETE => eliminar rutina
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRutina(@PathVariable int id) {
        Rutina rutina = servicioRutina.getRutinaById(id);
        if (rutina != null) {
            servicioRutina.borrarRutina(id);
            return new ResponseEntity<>("Rutina eliminada", HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>("Rutina no encontrada", HttpStatus.NOT_FOUND); // 404
        }
    }


    // PUT => actualizar ejercicio (lo marca como completado)
    @PutMapping("/ejercicios/{id}")
    public ResponseEntity<String> marcarCompletado(@PathVariable int id) {
        Ejercicio ejercicio = servicioEjercicio.traerEjercicio(id);
        if (ejercicio != null) {
            ejercicio.setCompletado();
            servicioEjercicio.guardarEjercicio(ejercicio);
            return new ResponseEntity<>("Estado actualizado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ejercicio no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
