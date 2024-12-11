package com.entornos.cubiculos_backend.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name="piso")
@Getter
@Setter
@ToString
public class Piso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name="numero_piso")
    private int numero_piso;


}
