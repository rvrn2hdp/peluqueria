package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Servicio;
import java.util.List;

/**
 *
 * @author RVRN2
 */
public interface IServicioService {

    public List<Servicio> buscarTodo();
    
    public List<Servicio> buscarActivos(boolean activo);
    
    public Servicio buscarPorId(Long id);
    
    public void guardar(Servicio servicio);

    public void deshabilitar(Long id);
}
