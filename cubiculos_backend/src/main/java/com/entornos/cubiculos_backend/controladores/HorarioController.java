package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.DTO.ConsultaPrincipal;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.servicios.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    private IHorarioService horarioService;

    @GetMapping("/consultaPrincipal")
    public ResponseEntity<List<ConsultaPrincipal>> consultaPrincipal(@RequestParam LocalDate fecha,
                                                                     @RequestParam(required = false) LocalDateTime fechaHoraInicio,
                                                                     @RequestParam(required = false) LocalDateTime fechaHoraFin,
                                                                     @RequestParam(required = false) Long capacidad){

        List<List<Horario>> horariosPorCubiculo = horarioService.consultaPrincipalHorarios(fecha, fechaHoraInicio, fechaHoraFin, capacidad);
        List<ConsultaPrincipal> cubiculosConDisponibilidad = new ArrayList<>();

        //Convertimos la lista de horarios por cubiculo en una lista de objetos ConsultaPrincipal para el front
        horariosPorCubiculo.forEach(horarios -> {
            ConsultaPrincipal consultaPrincipal = new ConsultaPrincipal();
            consultaPrincipal.setIdCubiculo(horarios.get(0).getCubiculo().getId());
            consultaPrincipal.setNumeroCubiculo(horarios.get(0).getCubiculo().getNumeroCubiculo());
            consultaPrincipal.setCapacidad(horarios.get(0).getCubiculo().getCapacidad());
            consultaPrincipal.setDescripcion(horarios.get(0).getCubiculo().getDescripcion());
            List<LocalDateTime> horariosDisponibles = new ArrayList<>();
            horarios.forEach(horario -> horariosDisponibles.add(horario.getFecha()));
            consultaPrincipal.setHorarios(horariosDisponibles);
            cubiculosConDisponibilidad.add(consultaPrincipal);
        });

        return new ResponseEntity<>(cubiculosConDisponibilidad, HttpStatus.OK);
    };

    @Autowired
    public void setHorarioService(IHorarioService horarioService){
        this.horarioService = horarioService;
    }
}