package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.exepciones.ValidationException;
import com.entornos.cubiculos_backend.modelos.Asistente;
import com.entornos.cubiculos_backend.modelos.Horario;
import com.entornos.cubiculos_backend.modelos.Reserva;
import com.entornos.cubiculos_backend.servicios.IHorarioService;
import com.entornos.cubiculos_backend.servicios.IReservaService;
import com.entornos.cubiculos_backend.serviciosImpl.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("http://localhost:4200")
public class ReservaController {
    private ReservaServiceImpl reservaService;


    @PostMapping("/confirmar")
    public ResponseEntity<Reserva> confirmarReserva(@RequestParam Long idCubiculo,
                                                    @RequestHeader("X-User-ID") Long idEstudiante,
                                                    @RequestParam LocalDateTime fechaHoraInicio,
                                                    @RequestParam LocalDateTime fechaHoraFin,
                                                    @RequestBody List<Asistente> asistentes ) {

        try {
            Reserva reserva = this.reservaService.createReserva(idCubiculo,idEstudiante,fechaHoraInicio,fechaHoraFin,asistentes);
            return ResponseEntity.ok(reserva);
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado: " + ex.getMessage());
        }
    }

    @GetMapping("/listarReservaEstudiante")
    public ResponseEntity<List<Horario>> listarReservaEstudiante(@RequestHeader("X-User-ID") Long idEstudiante) {
        if(this.reservaService.verReservasPorIdE(idEstudiante).isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.reservaService.verReservasPorIdE(idEstudiante));

    }





    @Autowired
    public void setReservaService(ReservaServiceImpl reservaService){
        this.reservaService = reservaService;
    }
}
