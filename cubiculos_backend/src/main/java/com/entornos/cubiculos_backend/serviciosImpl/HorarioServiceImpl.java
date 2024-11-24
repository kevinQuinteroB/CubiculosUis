package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.repositorios.ICubiculoRepository;
import com.entornos.cubiculos_backend.repositorios.IHorarioRepository;
import com.entornos.cubiculos_backend.servicios.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioServiceImpl implements IHorarioService {

    private IHorarioRepository horarioRepository;
    private ICubiculoRepository cubiculoRepository;

    final int horaApertura = 8;
    final int horaCierre = 18;
    private int diasSiguientes = 2;

    @Override
    @Scheduled(cron = "0 25 10 * * *")
    public void generarHorarioPorCubiculoParaDiaSiguiente() {

        // Verificamos que los horarios base ya existan para hoy y mañana
        if (horarioRepository.findAllByFecha(LocalDate.now()).isEmpty() ||
                horarioRepository.findAllByFecha(LocalDate.now().plusDays(1)).isEmpty()) {
            if(LocalDate.now().plusDays(1).getDayOfWeek().getValue() == 6){
                generarHorariosBase();
            }
        }

        //Definimos el dia para el cual se crearan los horarios
        LocalDateTime diaACrear = LocalDateTime.now().plusDays(diasSiguientes);
        //Si es jueves o viernes, se agregan dias para no crear el fin de semana
        if(LocalDate.now().getDayOfWeek().getValue() == 4 || LocalDate.now().getDayOfWeek().getValue() == 5){
            diaACrear = diaACrear.plusDays(2);
        }

        // generar horarios para cada cubiculo
        generarHorarioParaDia(diaACrear);

    }

    @Override
    public void generarHorariosBase() {

        //funcion para generar horarios base (horarios de hoy y mañana)
        for(int j=0; j<2; j++) {

            LocalDateTime diaACrear = LocalDateTime.now().plusDays(j);
            if(LocalDate.now().getDayOfWeek().getValue() == 6){
                diaACrear = diaACrear.plusDays(2);
            }
            // Crear horarios para cada cubiculo
            generarHorarioParaDia(diaACrear);
        }

    }

    public void generarHorarioParaDia(LocalDateTime diaACrear) {

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
    public List<List<Horario>> consultaPrincipalHorarios(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin, Long capacidad) {
        //Consulta principal de horarios
        List<Horario> horariosDisponibles = horarioRepository.buscadorPrincipal(fecha, horaInicio, horaFin, capacidad);
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
