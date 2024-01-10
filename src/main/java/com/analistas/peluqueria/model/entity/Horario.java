package com.analistas.peluqueria.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.Data;

/**
 *
 * @author RVRN2
 */

@Entity
@Data
@Table(name = "hor_disp")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalTime horManAp;
    
    private LocalTime horManFin;
    
    private LocalTime horTarAp;
    
    private LocalTime horTarFin;
    
    @Override
    public String toString() {
        return "Horario Disponible por la mañana: " + this.horManAp + " AM a " +
                this.horManFin + " PM.\nHorario Disponible por la tarde: " +
                this.horTarAp + " PM a " + this.horTarFin + " PM";
    }
    
}
