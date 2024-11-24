package com.entornos.cubiculos_backend.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "id_reserva", nullable = true)
    private Long idReserva;

    @Column(name="id_cubiculo")
    private Long idCubiculo;

    //Relaciones

    @JoinColumn(name = "id_cubiculo",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cubiculo cubiculo;

    @JoinColumn(name = "id_reserva",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Reserva reserva;
}
