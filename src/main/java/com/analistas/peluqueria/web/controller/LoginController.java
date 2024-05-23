package com.analistas.peluqueria.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // implementar Login con MySql y BCrypt encoder

    @GetMapping("/login")
    public String login(/* @RequestBody User loginForm */) {
        // Aquí puedes realizar la lógica de autenticación personalizada, si es necesario
        // Por ejemplo, puedes verificar las credenciales del usuario y realizar la autenticación manualmente

        // Obtener el objeto Authentication de Spring Security
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener los detalles del usuario autenticado
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Aquí puedes realizar cualquier acción adicional después de que el usuario se haya autenticado correctamente

        // Devolver una respuesta o redireccionar a una URL adecuada para el usuario autenticado
        return "usuarios/login";
    }
}