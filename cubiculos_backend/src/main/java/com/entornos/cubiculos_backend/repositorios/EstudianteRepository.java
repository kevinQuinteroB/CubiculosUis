package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
