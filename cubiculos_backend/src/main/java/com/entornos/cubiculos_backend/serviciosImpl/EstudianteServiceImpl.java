package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.exepciones.ValidationException;
import com.entornos.cubiculos_backend.modelos.Asistente;
import com.entornos.cubiculos_backend.modelos.Estudiante;
import com.entornos.cubiculos_backend.repositorios.EstudianteRepository;
import com.entornos.cubiculos_backend.servicios.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;


    @Scheduled(cron = "0 0 21 * * *")
    public String ResetearHorasDisponibles(){

        List<Estudiante> estudiantes = this.estudianteRepository.findEstudiantesHoras();
        if(estudiantes.isEmpty()){
            return "ok";
        }
        estudiantes.forEach(estudiante -> {
            estudiante.setHorasDisp(2);
            this.estudianteRepository.save(estudiante);
            actualizarEstudiante(estudiante);
        });
        return "ok";
    }

    @Override
    public void restarHorasDisponiblesAsistentes(List<Asistente> asistentes, int horas){

        for(Asistente asistente : asistentes){
            Estudiante estudiante = this.estudianteRepository
                    .findEstudianteByCodigoEstudiante(asistente.getCodigo());

            restarHorasDiponibles(horas,estudiante.getId());
        }
    }


    @Override
    public void restarHorasDiponibles(int horas, Long idEstudiante){
        Estudiante estudiante = this.estudianteRepository.findById(idEstudiante).get();
        int horasEstudiante = estudiante.getHorasDisp()-horas;
        if(estudiante.getHorasDisp() < horas){
            throw new ValidationException("HorasDiariasEstudiante");
        }
        estudiante.setHorasDisp(horasEstudiante);
        actualizarEstudiante(estudiante);
    }



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
