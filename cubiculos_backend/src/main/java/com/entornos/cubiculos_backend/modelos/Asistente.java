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

    @Column(name = "id_reserva")
    private long idReserva;

    @Column(name = "id_estudiante")
    private long idEstudiante;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante",insertable = false,updatable = false)
    private Estudiante estudiante;

    @JoinColumn(name = "id_reserva",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Reserva reserva;
}
