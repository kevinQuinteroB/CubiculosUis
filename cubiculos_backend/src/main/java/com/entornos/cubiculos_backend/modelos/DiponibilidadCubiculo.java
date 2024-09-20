package com.entornos.cubiculos_backend.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "disponibilidad_cubiculo")
public class DiponibilidadCubiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;


    @JoinColumn(name = "id_disponibilidad",nullable = false,insertable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Disponibilidad disponibilidad;

    @JoinColumn(name = "id_cubiculo",nullable = false,insertable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cubiculo cubiculo;
}
