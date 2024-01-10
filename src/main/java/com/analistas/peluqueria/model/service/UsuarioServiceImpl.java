package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Usuario;
import com.analistas.peluqueria.model.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RVRN2
 */

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodo() {
        return (List<Usuario>) usuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarEstilistas() {

        Iterable<Usuario> usuarios = usuarioRepo.findAll();

        List<Usuario> estilistas = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getIdRol().getDescripcion().equals("ROLE_ESTILISTA")) {
                estilistas.add(usuario);
            }
        }

        return estilistas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarClientes() {

        Iterable<Usuario> usuarios = usuarioRepo.findAll();

        List<Usuario> clientes = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getIdRol().getDescripcion().equals("ROLE_USUARIO")) {
                clientes.add(usuario);
            }
        }

        return clientes;
        /*
         * List<Usuario> clientes = StreamSupport.stream(usuarios.spliterator(), true)
         * .filter(obj -> obj.getIdRol().getDescripcion().equals("ROLE_ESTILISTA"))
         * .filter(obj -> obj.getIdRol().getDescripcion().equals("ROLE_ADMINISTRADOR"))
         * .collect(Collectors.toList());
         * 
         * return clientes;
         */
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorId(@NonNull Long id) {
        return usuarioRepo.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {

        Iterable<Usuario> usuarios = usuarioRepo.findAll();

        Usuario usuario = null;

        for (Usuario u : usuarios) {

            if (u.getEmail().equals(email)) {
                usuario = u;
                break;
            } else {
                usuario = null;
            }
        }

        return usuario;
    }

    @Override
    @Transactional()
    public void guardar(@NonNull Usuario usuario) {
        usuarioRepo.save(usuario);
    }

    @Override
    @Transactional()
    public void deshabilitar(@NonNull Long id) {
        // Buscar un usuario y deshabilitarlo.
        Usuario usuario = usuarioRepo.findById(id).orElseThrow();
        usuario.setActivo(false);
        usuarioRepo.save(usuario);
    }

}
