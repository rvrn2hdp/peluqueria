package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Horario;
import java.util.List;

/**
 *
 * @author RVRN2
 */
public interface IHorarioService {
    
    public List<Horario> buscarTodo();

    public Horario buscarPorId(Long id);

    public void guardar(Horario horario);

    public void borrar(Long id);
    
}
