package com.analistas.peluqueria.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author RVRN2
 */

@Controller
public class HomeController {
    
    @GetMapping({"/", "/index"})
    public String home(Model model) {
        
        model.addAttribute("titulo", "Peluqueria");
        
        return "plantilla";
    }
}
