package com.analistas.peluqueria.model.repository;

import com.analistas.peluqueria.model.entity.Servicio;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

/**
 *
 * @author RVRN2
 */
public interface IServicioRepository extends CrudRepository<Servicio, Long> {

    // crear una lista de servicios que están activos:
    //@Query(value = "SELECT s FROM servicio s WHERE s.activo = :activo")
    //List<Servicio> buscarActivos(@Param("activo") boolean activo);
}
