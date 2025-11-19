package com.example.demo2;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {
    
    @Autowired
    private GestorReservas gestorReservas;
    
    @GetMapping
    public String listarHabitaciones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/";
        }
        model.addAttribute("habitaciones", gestorReservas.getHabitaciones());
        return "lista-habitaciones";
    }
    
    @PostMapping
    public String crearHabitacion(@ModelAttribute Habitacion habitacion, 
                                 BindingResult result) {
        // Validar número único
        if (gestorReservas.getHabitaciones().stream()
            .anyMatch(h -> h.getNumero().equals(habitacion.getNumero()))) {
            result.rejectValue("numero", "error.habitacion", "Número de habitación duplicado");
        }
        
        if (!result.hasErrors()) {
            habitacion.setId(gestorReservas.getNextHabitacionId());
            gestorReservas.getHabitaciones().add(habitacion);
            return "redirect:/habitaciones";
        }
        return "form-habitacion";
    }
}