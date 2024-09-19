package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.serviciosImpl.CubiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cubiculo")
public class CubiculoController {
    @Autowired
    private CubiculoService cubiculoService;


    @PostMapping("")
    public ResponseEntity<Cubiculo> cargarCubiculo(@RequestBody Cubiculo cubiculo) {
       Cubiculo cub= this.cubiculoService.insertarCubiculo(cubiculo);
       return ResponseEntity.ok(cub);
    }

    @GetMapping("/prueba")
    public ResponseEntity<List<Integer>> listarCubiculos() {
        return ResponseEntity.ok(this.cubiculoService.getCubiculoPiso());
    }
}
