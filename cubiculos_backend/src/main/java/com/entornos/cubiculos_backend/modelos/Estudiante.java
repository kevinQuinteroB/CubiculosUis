package com.entornos.cubiculos_backend.modelos;


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

    @Column(name="estado")
    private int estado;


}
