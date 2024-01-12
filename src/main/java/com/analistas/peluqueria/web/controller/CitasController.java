/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.analistas.peluqueria.web.controller;

import com.analistas.peluqueria.model.entity.Cita;
import com.analistas.peluqueria.model.service.ICitaService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

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

/**
 *
 * @author RVRN2
 */

@Controller
@RequestMapping("/citas")
@SessionAttributes("cita")
public class CitasController {

    @Autowired()
    private ICitaService citaService;

    // Listar las Citas:
    @GetMapping("/lista")
    public String listar(Model model) {

        model.addAttribute("titulo", "Listado de Citas");
        model.addAttribute("citas", citaService.buscarTodo());
        model.addAttribute("citasactivas", citaService.buscarActivas());
        model.addAttribute("citasFecha", citaService.buscarPorFecha(LocalDateTime.now()));
        model.addAttribute("citasUserActivas", citaService.buscarActivasPorUsuario(1L));

        return "citas/lista";
    }

    // Crear una nueva Cita:
    @GetMapping("/nueva")
    public String nueva(Model model) {

        model.addAttribute("titulo", "Crear Nueva Cita");
        model.addAttribute("cita", new Cita());

        return "citas/form";
    }

    // Editar una Cita:
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if (cita == null) {
            return "redirect:/citas/lista";
        }

        model.addAttribute("titulo", "Editar Cita");
        model.addAttribute("cita", cita);

        return "citas/form";
    }

    // Guardar una Cita:
    @PostMapping("/guardar")
    public String guardar(@Valid Cita cita,
            BindingResult result,
            Model model, SessionStatus status,
            RedirectAttributes flash) {

        if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> {

                String messageError = error.getDefaultMessage();
                flash.addFlashAttribute("error", messageError);

                System.out.println(messageError);
            });

            model.addAttribute("titulo", "Error al guardar la Cita");
            model.addAttribute("danger", "Revise los datos");

            return "citas/form";
        }

        // Comprobar que la fecha y hora de la Cita no coincida con otra Cita activa:
        Iterable<Cita> citasActivas = citaService.buscarActivas();

        for (Cita c : citasActivas) {

            if (cita.getFechaHora().equals(c.getFechaHora())) {
                model.addAttribute("error", "La Hora y Fecha ya fue reservada...");
                return "citas/form";
            }
        }

        citaService.guardar(cita);

        status.setComplete();

        flash.addFlashAttribute("success", "Cita guardada");

        return "redirect:/citas/lista";
    }

    // Deshabilitar una Cita:
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id") long id, RedirectAttributes flash) {

        Cita cita = citaService.buscarPorId(id);

        if (cita == null) {
            flash.addFlashAttribute("error", "La Cita no existe");
            return "redirect:/citas/lista";
        }

        citaService.deshabilitar(id);

        return "redirect:/citas/lista";
    }
}
