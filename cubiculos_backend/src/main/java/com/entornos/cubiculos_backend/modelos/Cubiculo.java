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
@Table(name = "cubiculo")
public class Cubiculo {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    @Column(name="capacidad")
    private int capacidad;

    @Column(name="id_piso")
    private long idPiso;


    @JoinColumn(name ="id_piso",insertable = false,updatable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Piso piso;
}
