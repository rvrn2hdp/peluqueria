package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author RVRN2
 */

public interface IUsuarioService {
    
    public List<Usuario> buscarTodo();
    
    public List<Usuario> buscarEstilistas();
    
    public List<Usuario> buscarClientes();
    
    public Usuario buscarPorId(Long id);

    public Usuario buscarPorEmail(String email);
    
    public void guardar(Usuario usuario);
    
    public void deshabilitar(Long id);
}
