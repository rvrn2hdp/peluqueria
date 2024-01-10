package com.analistas.peluqueria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author RVRN2
 */
@Entity
@Data
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 300)
    @NotEmpty(message = "La descripcion es requerida...")
    private String descripcion;

    @NotNull(message = "El precio es requerido...")
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal precio;

    @NotEmpty(message = "La duración es requerida...")
    @Size(min = 5, max = 10)
    private String duracion;

    @Column(name = "activo", columnDefinition = "boolean default true")
    private boolean activo;

    public Servicio() {
        this.activo = true;
    }

    @Override
    public String toString() {

        return this.descripcion;
    }
}
