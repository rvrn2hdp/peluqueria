/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Servicio;
import com.analistas.peluqueria.model.repository.IServicioRepository;
import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.StreamSupport;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RVRN2
 */

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    IServicioRepository servicioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarTodo() {
        return (List<Servicio>) servicioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarActivos(boolean activo) {

        return servicioRepo.findByActivo(activo);

        // Iterable <Servicio> servicios = servicioRepo.findAll();

        // List<Servicio> activos = StreamSupport.stream(servicios.spliterator(), false)
        //     .filter(obj -> obj.isActivo() == activo)
        //     .collect(Collectors.toList());

        // return activos;
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio buscarPorId(@NonNull Long id) {
        return servicioRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public void guardar(@NonNull Servicio servicio) {
        servicioRepo.save(servicio);
    }

    @Override
    @Transactional()
    public void deshabilitar(@NonNull Long id) {
        // Buscar un servicio y deshabilitarlo.
        Servicio servicio = servicioRepo.findById(id).orElseThrow();
        servicio.setActivo(false);
        servicioRepo.save(servicio);
    }
}
