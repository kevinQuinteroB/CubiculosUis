package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
