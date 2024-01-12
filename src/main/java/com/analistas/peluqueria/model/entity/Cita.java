package com.analistas.peluqueria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author RVRN2
 */

@Entity
@Data
@Table(name = "cita")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 200, nullable = true)
    private String notas;
    
    @Column(name = "fecha_hora", unique = true)
    private LocalDateTime fechaHora;
    
    @Column(columnDefinition = "boolean default false")
    private boolean visita;

    @Column(columnDefinition = "boolean default true")
    private boolean activo;
    
    @NotNull(message = "El servicio debe ser incluido...")
    @OneToOne(fetch = FetchType.LAZY)
    private Servicio servicio;
    
    @NotNull(message = "El usuario debe ser incluido...")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    
}
