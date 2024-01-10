package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;

public interface ICitaService {

    public List<Cita> buscarTodo();

    public List<Cita> buscarActivas();

    public List<Cita> buscarActivasPorUsuario(Long id);

    public List<Cita> buscarPorFecha(LocalDateTime fechaHora);

    public Cita buscarPorId(long id);

    public void guardar(Cita cita);
    
    public void deshabilitar(long id);

}
