package com.analistas.peluqueria.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analistas.peluqueria.model.entity.Rol;
import com.analistas.peluqueria.model.repository.IRolRepository;

import lombok.NonNull;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> buscarTodo() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public List<Rol> buscarPor(String descripcion) {

        Iterable <Rol> roles = rolRepository.findAll();

        List<Rol> porDesc = new ArrayList<>();

        for (Rol rol : roles) {
            if (rol.getDescripcion().equals(descripcion)) {
                porDesc.add(rol);
            }
        }
        

        return porDesc;
    }

    @Override
    public Rol buscarPorId(@NonNull Long id) {
        return rolRepository.findById(id).get();
    }

    @Override
    public void guardar(@NonNull Rol rol) {
        rolRepository.save(rol);
    }
    
}
