/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.analistas.peluqueria.model.repository;

import com.analistas.peluqueria.model.entity.Rol;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

/**
 *
 * @author RVRN2
 */
public interface IRolRepository extends CrudRepository<Rol, Long> {

    // Buscar por descripción:
    //@Query(value = "SELECT r FROM rol r WHERE r.descripcion = %:descripcion%")
    //List<Rol> buscarPor(@Param("descripcion") String descripcion);
}
