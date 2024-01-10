package com.analistas.peluqueria.web.controller;

import com.analistas.peluqueria.model.entity.Servicio;
import com.analistas.peluqueria.model.service.IServicioService;
import jakarta.validation.Valid;
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
@RequestMapping("/servicios")
@SessionAttributes("servicio")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    // Listar los servicios:
    @RequestMapping("/lista")
    public String listar(Model model) {

        model.addAttribute("titulo", "Listado de Servicios");
        model.addAttribute("servicios", servicioService.buscarActivos(true));

        return "servicios/lista";
    }

    // Crear un nuevo servicio:
    @RequestMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Agregar Servicio");
        model.addAttribute("servicio", new Servicio());

        return "servicios/form";
    }

    // Editar un servicio:
    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model) {

        Servicio servicio = servicioService.buscarPorId(id);

        if (servicio == null) {

            model.addAttribute("error", "Ocurrió un error...");

            return "redirect:/servicios/lista";
        }

        model.addAttribute("titulo", "Editar Servicio");
        model.addAttribute("servicio", servicio);

        return "servicios/form";
    }

    // Guardar un nuevo servicio:
    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio,
            BindingResult result,
            Model model, SessionStatus status,
            RedirectAttributes flash) {

        if (result.hasErrors()) {

            model.addAttribute("titulo", "Error al agregar Servicio");
            model.addAttribute("danger", "Revise los datos");

            return "servicios/form";
        }

        servicioService.guardar(servicio);

        status.setComplete();

        flash.addFlashAttribute("success", "Servicio guardado");

        return "redirect:/servicios/lista";
    }

    // Deshabilitar un servicio:
    @GetMapping("borrar/{id}")
    public String borrar(@PathVariable("id") long id, RedirectAttributes flash) {

        Servicio servicio = servicioService.buscarPorId(id);

        if (servicio == null) {
            flash.addFlashAttribute("error", "El Servicio no existe");
            return "redirect:/servicios/lista";
        }

        servicioService.deshabilitar(id);

        return "redirect:/servicios/lista";
    }

}
