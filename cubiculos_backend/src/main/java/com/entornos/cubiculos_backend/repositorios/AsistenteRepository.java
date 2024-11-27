package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
}
