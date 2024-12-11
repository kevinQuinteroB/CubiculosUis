package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Operario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperarioRepository extends JpaRepository<Operario, Long> {
}
