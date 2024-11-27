package com.entornos.cubiculos_backend.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(name = "id_estudiante")
    private long idEstudiante;



    @Column(name = "id_operario", nullable = true)
    private Long idOperario;

    @Column(name = "id_estado_reserva")
    private long idEstadoReserva;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_reserva",insertable = false,updatable = false)
    private EstadoReserva estadoReserva;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante",insertable = false,updatable = false)
    private Estudiante estudiante;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_operario", insertable = false, updatable = false)
    private Operario operario;


}
