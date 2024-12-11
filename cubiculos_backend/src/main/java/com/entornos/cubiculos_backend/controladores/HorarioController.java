package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.DTO.ConsultaPrincipal;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.servicios.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ConsultaPrincipal>> consultaPrincipal(@RequestParam LocalDateTime fechaHoraInicio,
                                                                     @RequestParam LocalDateTime fechaHoraFin,
                                                                     @RequestParam Long capacidad){

        List<List<Horario>> horariosPorCubiculo = horarioService.consultaPrincipalHorarios(fechaHoraInicio, fechaHoraFin, capacidad);
        List<ConsultaPrincipal> cubiculosConDisponibilidad = new ArrayList<>();

        //Convertimos la lista de horarios por cubiculo en una lista de objetos ConsultaPrincipal para el front
        horariosPorCubiculo.forEach(horarios -> {
            ConsultaPrincipal consultaPrincipal = new ConsultaPrincipal();
            consultaPrincipal.setIdCubiculo(horarios.get(0).getCubiculo().getId());
            consultaPrincipal.setNumeroCubiculo(horarios.get(0).getCubiculo().getNumeroCubiculo());
            consultaPrincipal.setCapacidad(horarios.get(0).getCubiculo().getCapacidad());
            consultaPrincipal.setDescripcion(horarios.get(0).getCubiculo().getDescripcion());
            consultaPrincipal.setFechaHoraInicio(horarios.get(0).getFecha());
            consultaPrincipal.setFechaHoraFin(horarios.get(horarios.size()-1).getFecha().plusHours(1));
            cubiculosConDisponibilidad.add(consultaPrincipal);
        });

        return new ResponseEntity<>(cubiculosConDisponibilidad, HttpStatus.OK);
    };

    @Autowired
    public void setHorarioService(IHorarioService horarioService){
        this.horarioService = horarioService;
    }
}
