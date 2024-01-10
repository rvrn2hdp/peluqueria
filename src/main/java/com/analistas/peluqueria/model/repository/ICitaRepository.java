package com.analistas.peluqueria.model.repository;



import com.analistas.peluqueria.model.entity.Cita;
//import java.time.LocalDateTime;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

public interface ICitaRepository extends CrudRepository<Cita, Long> {

    // Buscar las citas activas.
    //@Query("select c from Cita c where c.activo = true")
    //List<Cita> buscarActivas();

    // Buscar las citas activas por id del usuario.
    //@Query("select c from Cita c where c.activo = true and c.usuario.id = %:id%")
    //List<Cita> buscarActivasPorUsuario(@Param("id") long id);

    // Buscar las citas segun la fecha y hora.
    //@Query("select c from Cita c where c.fecha_hora = %:fecha_hora%")
    //List<Cita> findByFechaHoraList(@Param("fecha_hora") LocalDateTime fecha_hora);

    
}
