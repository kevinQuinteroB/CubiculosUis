package com.entornos.cubiculos_backend.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "codigo")
    private int codigo;

    @Column(name="id_estado_estudiante")
    private long idEstado;

    @JsonIgnore
    @Column(name = "horas_disp", nullable = false, columnDefinition = "integer default 2")
    private int horasDisp = 2;

    @Column(name = "phone", nullable = false)
    private String phone;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_estudiante",insertable = false,updatable = false)
    private EstadoEstudiante estadoEstudiante;


}
