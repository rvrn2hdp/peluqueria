package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Cita;
import com.analistas.peluqueria.model.repository.ICitaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarTodo() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarActivas() {

        /*
         * Iterable <Cita> citas = citaRepository.findAll();
         * 
         * List<Cita> activas = StreamSupport.stream(citas.spliterator(), false)
         * .filter(obj -> obj.isActivo())
         * .collect(Collectors.toList());
         * 
         * return activas;
         */

        return citaRepository.findByActivo(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarActivasPorUsuario(Long id) {

        // Iterable <Cita> citas = citaRepository.findAll();

        // Crear una lista de citas que pertenescan a un usuario y esten activas:

        /*
         * List<Cita> porUsuario = new ArrayList<>();
         * 
         * for (Cita cita : citas) {
         * 
         * if (cita.getId().equals(id) && cita.isActivo()) {
         * porUsuario.add(cita);
         * }
         * }
         * return porUsuario;
         */

        return citaRepository.findByActivoAndUsuarioId(true, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarPorFecha(LocalDateTime fechaHora) {
        LocalDate fecha = fechaHora.toLocalDate(); // Obtener solo la fecha sin la hora

        LocalDateTime fechaInicio = fecha.atStartOfDay(); // Hora de inicio del día
        LocalDateTime fechaFin = fecha.atTime(LocalTime.MAX); // Hora de fin del día

        return citaRepository.findByFechaHoraBetweenAndActivo(fechaInicio, fechaFin, true);
    }

    @Override
    @Transactional(readOnly = true)
    public Cita buscarPorId(long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public void guardar(@NonNull Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    @Transactional()
    public void deshabilitar(long id) {
        // Buscar una cita y deshabilitarla.
        Cita cita = citaRepository.findById(id).orElseThrow();
        cita.setActivo(false);
        citaRepository.save(cita);
    }

}
