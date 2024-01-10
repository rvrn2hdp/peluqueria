package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Rol;
import java.util.List;

/**
 *
 * @author RVRN2
 */
public interface IRolService {

    public List<Rol> buscarTodo();
    
    public List<Rol> buscarPor(String descripcion);
    
    public Rol buscarPorId(Long id);
    
    public void guardar(Rol rol);
}
