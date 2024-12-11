package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.serviciosImpl.CubiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cubiculo")
public class CubiculoController {
    @Autowired
    private CubiculoService cubiculoService;


    @PostMapping("/crear")
    public ResponseEntity<Cubiculo> crearCubiculo(@RequestBody Cubiculo cubiculo) {
       return new ResponseEntity<>(this.cubiculoService.crearCubiculo(cubiculo), HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Cubiculo> actualizarCubiculos(@RequestBody Cubiculo cubiculo) {
        return new ResponseEntity<>(this.cubiculoService.actualizarInformacionCubiculo(cubiculo), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarCubiculo(@RequestParam Long id) {
        this.cubiculoService.eliminarCubiculo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cubiculo>> listarCubiculos(@RequestParam(required = false) Long idPiso,
                                                         @RequestParam(required = false) Integer capacidad,
                                                         @RequestParam(required = false) String numeroCubiculo,
                                                         @RequestParam(required = false) Long idEstadoCubiculo) {
        return new ResponseEntity<>(cubiculoService.listarCubiculos(idPiso, capacidad, numeroCubiculo, idEstadoCubiculo), HttpStatus.OK);
    }
}
