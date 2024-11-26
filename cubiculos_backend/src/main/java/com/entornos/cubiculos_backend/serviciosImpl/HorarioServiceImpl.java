package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.exepciones.BadRequestException;
import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.repositorios.ICubiculoRepository;
import com.entornos.cubiculos_backend.repositorios.IHorarioRepository;
import com.entornos.cubiculos_backend.servicios.IHorarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HorarioServiceImpl implements IHorarioService {

    private IHorarioRepository horarioRepository;
    private ICubiculoRepository cubiculoRepository;

    final int horaApertura = 8;
    final int horaCierre = 19;
    private int rangoDeDias = 3;
    private int rangoDeHoras = 2;

    //Para que cada que se inicie la aplicacion revise que esten los horarios correctamente generados
    @PostConstruct
    public void init() {
        generarHorariosParaProximosDiasHabiles();
    }

    @Override
    @Scheduled(cron = "0 01 00 * * *")
    public void generarHorariosParaProximosDiasHabiles() {
        int diasModificados = 0;
        for (int j = 0; j < this.rangoDeDias; j++) {
            LocalDateTime diaACrear = LocalDateTime.now().plusDays(j);
            // Si es sabado y domingo se ajusta al siguiente día
            if (diaACrear.getDayOfWeek().getValue() == 6) {
                diaACrear = diaACrear.plusDays(2);
            } else if (diaACrear.getDayOfWeek().getValue() == 7) {
                diaACrear = diaACrear.plusDays(1);
            }
            // Comprueba que sea un día entre semana (Lunes a viernes)
            if (diaACrear.getDayOfWeek().getValue() >= 1 && diaACrear.getDayOfWeek().getValue() <= 5) {
                // Verfica si los horarios ya existen, si no existen los crea
                if (horarioRepository.findAllByFecha(diaACrear.toLocalDate()).isEmpty()) {
                    this.crearHorariosParaDia(diaACrear);
                    diasModificados++;
                    Logger.getGlobal().info("Se generaron los horarios para el día: " + diaACrear.toLocalDate());
                }
            }
        }
        if (diasModificados > 0) {
            Logger.getGlobal().info("En total se generaron los horarios para " + diasModificados + " días");
        }else{
            Logger.getGlobal().info("NO FUE NECESARIO GENERAR NUEVOS HORARIOS PARA LOS CUBICULOS");
        }
    }

    @Override
    public void crearHorariosParaDia(LocalDateTime diaACrear) {

        List<Cubiculo> cubiculos = cubiculoRepository.findAll();

        for (Cubiculo cubiculo : cubiculos) {
            //Creamos horarios desde la hora de apertura hasta la hora de cierre
            for (int i = horaApertura; i < horaCierre; i++) {
                Horario horario = new Horario();
                horario.setFecha(diaACrear
                        .withHour(i)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0)
                );
                horario.setIdCubiculo(cubiculo.getId());
                horarioRepository.save(horario);
            }
        }
    }

    @Override
    public List<List<Horario>> consultaPrincipalHorarios(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Long capacidad) {

        if((fechaHoraFin.getHour() - fechaHoraInicio.getHour()) > rangoDeHoras){
            throw new BadRequestException("El rango entre la hora de inicio y la hora de fin no puede ser mayor a " + rangoDeHoras);
        }

        //Consulta principal de horarios
        List<Horario> horariosDisponibles = horarioRepository.buscadorPrincipal(fechaHoraInicio, fechaHoraFin, capacidad);
        List<List<Horario>> horariosPorCubiculo = new ArrayList<>();
        horariosDisponibles.stream()
                .collect(Collectors.groupingBy(horario -> horario.getCubiculo().getId()))
                .forEach((idCubiculo, horariosCubiculo) -> horariosPorCubiculo.add(horariosCubiculo));
        return horariosPorCubiculo;
    }

    @Autowired
    public void setHorarioRepository(IHorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Autowired
    public void setCubiculoRepository(ICubiculoRepository cubiculoRepository) {
        this.cubiculoRepository = cubiculoRepository;
    }
}
