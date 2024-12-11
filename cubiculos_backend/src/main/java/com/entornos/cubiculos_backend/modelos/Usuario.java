package com.entornos.cubiculos_backend.modelos;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Data
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre_de_usuario", unique=true)
    private String nombreDeUsuario;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "email", unique=true)
    private String email;
}
