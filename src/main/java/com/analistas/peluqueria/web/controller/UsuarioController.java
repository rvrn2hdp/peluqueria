package com.analistas.peluqueria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import com.analistas.peluqueria.model.entity.Rol;
import com.analistas.peluqueria.model.entity.Usuario;
import com.analistas.peluqueria.model.service.IRolService;
import com.analistas.peluqueria.model.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

//    @Autowired
//    private BCryptPasswordEncoder bCrypt;

    // Listar los usuarios:
    @GetMapping("/lista")
    public String listar(Model model) {

        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarioService.buscarTodo());
        model.addAttribute("estilistas", usuarioService.buscarEstilistas());
        model.addAttribute("clientes", usuarioService.buscarClientes());
        // cargar un usuario segun su email
        model.addAttribute("usuario", usuarioService.buscarPorEmail("clienteuno@email.com"));

        return "usuarios/lista";
    }

    // Crear un nuevo usuario:
    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Crear nuevo usuario");
        model.addAttribute("usuario", new Usuario());

        return "usuarios/form";
    }

    // Editar un usuario:
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model, RedirectAttributes flash) {

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {

            flash.addFlashAttribute("error", "Ocurrió un error");

            return "redirect:/index";
        }

        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("usuario", usuario);

        return "usuarios/form";
    }

    // Guardar un usuario:
    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, BindingResult result,
            @RequestParam("idRol") Long idRol, Model model, SessionStatus status,
            RedirectAttributes flash) {

        if (result.hasErrors()) {

            model.addAttribute("titulo", "Error al guardar el usuario");

            return "usuarios/form";
        }

        Long i = usuario.getId();

        // Comprobar si se está guardando un nuevo usuario o editando uno existente:
        String action = i == null ? "creado" : "editado";

        // Encriptar la contraseñas:
//        usuario.setClave(bCrypt.encode(usuario.getClave()));

        // Asignar el rol al usuario:
        usuario.setIdRol(rolService.buscarPorId(idRol));

        flash.addFlashAttribute("success", "El usuario se ha " + action + " correctamente");

        usuarioService.guardar(usuario);

        status.setComplete();

        return "redirect:/index";
    }

    // Eliminar un usuario:
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id") Long id, RedirectAttributes flash) {

        usuarioService.deshabilitar(id);

        flash.addFlashAttribute("success", "El usuario ha sido eliminado correctamente");

        return "redirect:/index";
    }

    // Obtener la Lista de Roles para el formulario:
    @ModelAttribute("roles")
    public List<Rol> roles() {
        return rolService.buscarTodo();
    }
}
