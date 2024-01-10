/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.analistas.peluqueria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author RVRN2
 */
@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre es requerido...")
    @Size(min = 5, max = 50)
    private String nombre;

    @NotNull(message = "La clave es requerida...")
    @Size(min = 5, max = 80)
    private String clave;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private Rol idRol;

    @NotEmpty(message = "El correo es obligatorio...")
    @Size(max = 120)
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "El Número de telefono es requerido...")
    @Size(max = 13)
    private String telefono;

    @Size(max = 120)
    private String direccion;

    @Size(max = 200)
    private String notas;
    
    private String genero;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private boolean activo;

    public Usuario() {

        this.activo = true;
    }

    @Override
    public String toString() {

        return this.nombre + " - " + this.idRol;
    }

}
