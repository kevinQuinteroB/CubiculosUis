package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CubiculoRepository extends JpaRepository<Cubiculo, Long> {

}
