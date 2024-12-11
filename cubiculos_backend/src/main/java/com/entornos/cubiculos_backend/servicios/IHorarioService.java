package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Horario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IHorarioService {

    void generarHorariosParaProximosDiasHabiles();

    void crearHorariosParaDia(LocalDateTime diaACrear);

    List<List<Horario>> consultaPrincipalHorarios(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Long capacidad);

    boolean validarDiponibilidadHoraria(Long idCubiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Horario> listarHorarioPorIdReserva(Long idReserva);

    Horario retornarHorarioUnicoPorIdReserva(Long idReserva);
}
