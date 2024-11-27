package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Asistente;
import com.entornos.cubiculos_backend.modelos.Reserva;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IReservaService {
    @Transactional
    Reserva createReserva(Long idCubiculo, Long idEstudiante, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, List<Asistente> asistentes);

    boolean validarHoraReserva(LocalDateTime fechaHoraInicio);
    boolean validarCantidadAsistentes(List<Asistente> asistentes, Long idCubiculo);
}
