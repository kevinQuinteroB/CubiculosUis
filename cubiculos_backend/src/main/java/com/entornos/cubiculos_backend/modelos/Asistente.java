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
@Table(name = "asistente")
public class Asistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @Column(name = "id_reserva", nullable = true)
    private long idReserva;


    @Column(name="codigo_estudiante")
    private long codigo;


    @JoinColumn(name = "id_reserva",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Reserva reserva;
}
