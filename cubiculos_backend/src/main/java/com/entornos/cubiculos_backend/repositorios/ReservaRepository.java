package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("select r from Reserva r where r.idEstudiante =:idEstudiante")
    List<Reserva> findByEstudiante(@Param("idEstudiante")Long idEstudiante);

}
