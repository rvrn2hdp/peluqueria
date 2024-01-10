package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Cita;
import com.analistas.peluqueria.model.repository.ICitaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

        Iterable <Cita> citas = citaRepository.findAll();

        List<Cita> activas = StreamSupport.stream(citas.spliterator(), false)
            .filter(obj -> obj.isActivo())
            .collect(Collectors.toList());

        return activas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarActivasPorUsuario(Long id) {

        Iterable <Cita> citas = citaRepository.findAll();

        // Crear una lista de citas que pertenescan a un usuario y esten activas:

        List<Cita> porUsuario = new ArrayList<>();

        for (Cita cita : citas) {

            if (cita.getId().equals(id) && cita.isActivo()) {
                porUsuario.add(cita);
            }
        }

        return porUsuario;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarPorFecha(LocalDateTime fechaHora) {

        Iterable <Cita> citas = citaRepository.findAll();

        // Crear una lista de citas que pertenescan a una fecha dada:
        List<Cita> porFecha = StreamSupport.stream(citas.spliterator(), false)
            .filter(obj -> obj.getFechaHora().toLocalDate().equals(fechaHora.toLocalDate()))
            .collect(Collectors.toList());

        return porFecha;
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
