package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICubiculoRepository extends JpaRepository<Cubiculo, Long> {
    Optional<Cubiculo> findByNumeroCubiculo(String numeroCubiculo);

    @Query("SELECT c FROM Cubiculo c WHERE (:idPiso IS NULL OR c.piso.id = :idPiso) " +
            "AND (:capacidad IS NULL OR c.capacidad = :capacidad) " +
            "AND (:numeroCubiculo IS NULL OR c.numeroCubiculo = :numeroCubiculo) " +
            "AND (:idEstadoCubiculo IS NULL OR c.idEstadoCubiculo = :idEstadoCubiculo)")
    List<Cubiculo> buscarCubiculos(@Param("idPiso") Long idPiso,
                                   @Param("capacidad") Integer capacidad,
                                   @Param("numeroCubiculo") String numeroCubiculo,
                                   @Param("idEstadoCubiculo") Long idEstadoCubiculo);
}
