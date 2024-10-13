package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EstudianteService {

    List<Estudiante> getEstudiantes();
    Optional<Estudiante> getEstudiante(long id);
    void borrarEstudiante(long id);
    Estudiante actualizarEstudiante(Estudiante estudiante);
    Estudiante crearEstudiante(Estudiante estudiante);
}
