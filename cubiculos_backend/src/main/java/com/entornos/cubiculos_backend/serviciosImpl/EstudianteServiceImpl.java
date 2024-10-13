package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.modelos.Estudiante;
import com.entornos.cubiculos_backend.repositorios.EstudianteRepository;
import com.entornos.cubiculos_backend.servicios.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> getEstudiantes() {
        return this.estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudiante(long id) {
        return this.estudianteRepository.findById(id);
    }

    public void borrarEstudiante(long id) {
        this.estudianteRepository.deleteById(id);
    }

    public Estudiante crearEstudiante(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        Estudiante nuevoEstudiante =getEstudiante(estudiante.getId()).
                orElseThrow(()->new NoSuchElementException("No se encontró el estudiante"));

        nuevoEstudiante.setNombre(estudiante.getNombre());
        nuevoEstudiante.setCodigo(estudiante.getCodigo());
        nuevoEstudiante.setIdEstado(estudiante.getIdEstado());

        return this.estudianteRepository.save(nuevoEstudiante);
    }
}
