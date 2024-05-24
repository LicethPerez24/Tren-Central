package com.apiTren.crudJava.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "viajes")
@Getter
@Setter
public class ViajesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origen;

    private String destino;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Double precio;

}