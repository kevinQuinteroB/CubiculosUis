package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("select e From Estudiante e where e.horasDisp!=2")
    public List<Estudiante> findEstudiantesHoras();

    @Query("select  e From Estudiante e where e.codigo = :codigoEstudiante")
    public Estudiante findEstudianteByCodigoEstudiante(Long codigoEstudiante);

}
