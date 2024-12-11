package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.exepciones.ValidationException;
import com.entornos.cubiculos_backend.modelos.Asistente;
import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.modelos.Reserva;
import com.entornos.cubiculos_backend.repositorios.AsistenteRepository;
import com.entornos.cubiculos_backend.repositorios.ICubiculoRepository;
import com.entornos.cubiculos_backend.repositorios.ReservaRepository;
import com.entornos.cubiculos_backend.servicios.EstudianteService;
import com.entornos.cubiculos_backend.servicios.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaServiceImpl implements IReservaService {

    private ICubiculoRepository cubiculoRepository;
    private HorarioServiceImpl horarioService;
    private ReservaRepository reservaRepository;
    private AsistenteRepository asistenteRepository;
    private EstudianteService estudianteService;

    @Override
    @Transactional
    public Reserva createReserva(Long idCubiculo, Long idEstudiante, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFinfront, List<Asistente> asistentes) {

        LocalDateTime fechaHoraFin = fechaHoraFinfront.minusHours(1);
        if(fechaHoraFin.isBefore(fechaHoraInicio)) {
            throw new ValidationException("Fecha y hora fin no es valida");
        }
        int horas=0;

        //validaciones
        if(!validarCantidadAsistentes(asistentes, idCubiculo)) throw  new ValidationException("cantidad");
        if(!this.horarioService.validarDiponibilidadHoraria(idCubiculo,fechaHoraInicio,fechaHoraFin)) throw  new ValidationException("horario");
        if(!validarHoraReserva(fechaHoraInicio)) throw  new ValidationException("hora");

        //crear reserva y asignar idReserva a los asistentes
        Reserva reserva = new Reserva();
        reserva.setIdEstadoReserva(2);
        reserva.setIdEstudiante(idEstudiante);
        reserva.setIdOperario(null);
        Reserva reservaFinal = this.reservaRepository.save(reserva);
        asistentes.forEach(asistente ->{
            asistente.setIdReserva(reservaFinal.getId());
            this.asistenteRepository.save(asistente);
        });

        //restar horas disponibles

        if (fechaHoraInicio.isEqual(fechaHoraFin)){
            horas=1;
        }else{
            horas=2;
        }
        this.estudianteService.restarHorasDiponibles(horas,idEstudiante);
        this.estudianteService.restarHorasDisponiblesAsistentes(asistentes,horas);

        //vincular la reserva al horario
        this.horarioService.vincularHorarioReserva(idCubiculo, reserva.getId(),fechaHoraInicio,fechaHoraFin);

        return reservaFinal;

    }


    public List<Horario>verReservasPorIdE(Long idEstudiante){

        List<Reserva> reservas=this.reservaRepository.findByEstudiante(idEstudiante);
        if (reservas.stream().map(Reserva::getId).distinct().count() ==1){
            return this.horarioService.listarHorarioPorIdReserva(reservas.get(0).getId());

        }else{
            List<Horario> horarios= new ArrayList<>();
            for (Reserva re : reservas) {
                // Obtenemos horarios asociados a cada reserva
                List<Horario> horariosPorReserva = this.horarioService.listarHorarioPorIdReserva(re.getId());

                // Filtramos horarios únicos basándonos en una propiedad única de `Horario`
                horariosPorReserva.stream()
                        .filter(horario -> horarios.stream().noneMatch(h -> h.getId().equals(horario.getId())))
                        .forEach(horarios::add);
            }
            return horarios;

        }
    }

    @Override
    public boolean validarHoraReserva(LocalDateTime fechaHoraInicio){
        if(LocalDateTime.now().isBefore(fechaHoraInicio.plusMinutes(30))) return true;
        return false;
    }

    @Override
    public boolean validarCantidadAsistentes(List<Asistente> asistentes, Long idCubiculo) {
        Cubiculo cubiculo = cubiculoRepository.findById(idCubiculo).get();
        if (cubiculo.getCapacidad() == asistentes.size()+1) {
            return true;
        }
        return false;
    }




    @Autowired
    public void setCubiculoRepository(ICubiculoRepository cubiculoRepository) {
        this.cubiculoRepository = cubiculoRepository;
    }

    @Autowired
    public void setEstudianteService(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }


    @Autowired
    public void setAsistenteRepository(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    @Autowired
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Autowired
    public void setHorarioService(HorarioServiceImpl horarioService) {
        this.horarioService = horarioService;
    }
}
