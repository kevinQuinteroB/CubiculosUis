package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IHorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT h FROM Horario h WHERE DATE(h.fecha) = :fecha")
    List<Horario> findAllByFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT h FROM Horario h " +
            "JOIN Cubiculo c ON h.idCubiculo = c.id " +
            "WHERE h.idReserva IS NULL " +
            "AND (h.fecha >= :fechaHoraInicio) " +
            "AND ( h.fecha < :fechaHoraFin) " +
            "AND ( c.capacidad = :capacidad) "
    )
    List<Horario> buscadorPrincipal(    @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                          @Param("fechaHoraFin") LocalDateTime fechaHoraFin,
                                          @Param("capacidad") Long capacidad
    );


    @Query("SELECT h FROM Horario h " +
            "WHERE h.idCubiculo = :idCubiculo " +
            "AND  h.fecha >= :fechaHoraInicio " +
            "AND  h.fecha < :fechaHoraFin"
    )
    List<Horario> buscarHorarioPorCubiculo( @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                            @Param("fechaHoraFin") LocalDateTime fechaHoraFin,
                                            @Param("idCubiculo") Long idCubiculo
    );


    @Query("SELECT h FROM Horario h " +
            "WHERE h.idCubiculo = :idCubiculo " +
            "AND  h.fecha = :fechaHoraInicio "
    )
    List<Horario> buscarHorarioPorCubiculoUna( @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                            @Param("idCubiculo") Long idCubiculo
    );


}
