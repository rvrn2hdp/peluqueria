package com.analistas.peluqueria.model.service;

import com.analistas.peluqueria.model.entity.Horario;
import com.analistas.peluqueria.model.repository.IHorarioRepository;
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
public class HorarioServiceImpl implements IHorarioService {

    @Autowired
    IHorarioRepository horarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Horario> buscarTodo() {
        return (List<Horario>) horarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Horario buscarPorId(@NonNull Long id) {
        return horarioRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public void guardar(@NonNull Horario horario) {
        horarioRepo.save(horario);
    }

    @Override
    @Transactional()
    public void borrar(@NonNull Long id) {
        horarioRepo.deleteById(id);
    }
}
