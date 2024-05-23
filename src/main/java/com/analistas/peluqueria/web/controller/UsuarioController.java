package com.analistas.peluqueria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.analistas.peluqueria.model.entity.Rol;
import com.analistas.peluqueria.model.entity.Usuario;
import com.analistas.peluqueria.model.service.IRolService;
import com.analistas.peluqueria.model.service.IUsuarioService;

//import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

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
    @Secured("ROLE_USER")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Crear nuevo usuario");
        model.addAttribute("usuario", new Usuario());

        return "usuarios/form";
    }

    // Editar un usuario:
    @GetMapping("/editar/{id}")
    // @Secured("ROLE_USER")
    // public String editar(@PathVariable("id") long id, Model model, RedirectAttributes flash) {
    public ResponseEntity<?> editar(@PathVariable("id") long id, Model model, RedirectAttributes flash) {
        

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe");

            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        return ResponseEntity.ok(usuario);
    }

    // Guardar un usuario:
    @PostMapping("/guardar")
    //@Secured("ROLE_USER")
    // public String guardar(@Valid Usuario usuario, BindingResult result,
    //         @RequestParam("idRol") Long idRol, Model model, SessionStatus status,
    //         RedirectAttributes flash) {
    public ResponseEntity<?> guardar(@Valid Usuario usuario, BindingResult result,
            RedirectAttributes flash) {
                
        if (result.hasErrors()) {
            
            Map<String, String> errores = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                
                errores.put(error.getField(), error.getDefaultMessage());

            }

            return ResponseEntity.unprocessableEntity().body(errores);
        }

        usuario.setActivo(true);
        usuario.setClave(bCrypt.encode(usuario.getClave()));

        usuarioService.guardar(usuario);

        return ResponseEntity.ok().build();
    }

    // Eliminar un usuario:
    @GetMapping("/borrar/{id}")
    // @Secured("ROLE_USER")
    // public String borrar(@PathVariable("id") Long id, RedirectAttributes flash) {
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
    
        usuarioService.deshabilitar(id);

        return ResponseEntity.ok().build();
    }

    // Obtener la Lista de Roles para el formulario:
    @ModelAttribute("roles")
    public List<Rol> roles() {
        return rolService.buscarTodo();
    }
}
