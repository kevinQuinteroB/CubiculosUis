package com.entornos.cubiculos_backend.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "operario")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "estado",nullable = false)
    private int estado;
}
