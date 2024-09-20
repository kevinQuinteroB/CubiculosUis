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
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "codigo")
    private int codigo;

    @Column(name="id_estado_estudiante")
    private long idEstado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_estudiante",insertable = false,updatable = false)
    private EstadoEstudiante estadoEstudiante;


}
