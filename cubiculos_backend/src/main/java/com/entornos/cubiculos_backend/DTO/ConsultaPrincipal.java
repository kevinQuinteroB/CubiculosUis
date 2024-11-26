package com.entornos.cubiculos_backend.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ConsultaPrincipal {
    private Long idCubiculo;
    private String numeroCubiculo;
    private Integer capacidad;
    private String descripcion;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
}
