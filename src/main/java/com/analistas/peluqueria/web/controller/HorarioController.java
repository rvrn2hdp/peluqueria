package com.analistas.peluqueria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.peluqueria.model.entity.Horario;
import com.analistas.peluqueria.model.service.IHorarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/horarios")
@SessionAttributes("horario")
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;

    // Listar los horarios:
    @GetMapping("/lista")
    public String listar(Model model) {

        model.addAttribute("titulo", "Listado de Horarios");
        model.addAttribute("horarios", horarioService.buscarTodo());

        return "horarios/lista";
    }

    // Agregar un nuevo horario:
    @GetMapping("/nuevo")
    public String agregar(Model model) {

        model.addAttribute("titulo", "Agregar Horario");
        model.addAttribute("horario", new Horario());

        return "horarios/form";
    }

    // Editar un horario:
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model) {

        model.addAttribute("horario", horarioService.buscarPorId(id));
        model.addAttribute("titulo", "Editar Horario");

        return "horarios/form";
    }

    // Guardar un horario:
    @PostMapping("/guardar")
    public String guardar(@Valid Horario horario, Model model, SessionStatus status,
            RedirectAttributes flash, BindingResult result) {

        if (result.hasErrors()) {

            model.addAttribute("titulo", "Error al agregar Horario");

            return "horarios/form";
        }

        flash.addFlashAttribute("success", "Horario guardado");

        horarioService.guardar(horario);

        return "redirect:/horario/lista";
    }
}