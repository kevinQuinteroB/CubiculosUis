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

    @Column(name = "id_disponibilidad")
    private long idDisponibilidad;

    @Column(name="id_cubiculo")
    private long idCubiculo;

    @JoinColumn(name = "id_disponibilidad",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Disponibilidad disponibilidad;

    @JoinColumn(name = "id_cubiculo",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cubiculo cubiculo;
}
