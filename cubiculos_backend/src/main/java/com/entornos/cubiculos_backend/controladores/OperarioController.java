package com.entornos.cubiculos_backend.controladores;


import com.entornos.cubiculos_backend.modelos.Operario;
import com.entornos.cubiculos_backend.servicios.OperarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/operario")
public class OperarioController {
    @Autowired
    private OperarioService operarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Operario>> listar(){
        if (this.operarioService.getOperarios().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.operarioService.getOperarios());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> borrarOperario(@RequestParam Long id){
        if (this.operarioService.getOperarios().isEmpty()) return ResponseEntity.noContent().build();
        this.operarioService.borrarOperario(id);
        return ResponseEntity.ok("Operario eliminado");
    }

    @PostMapping("/crear")
    public ResponseEntity<Operario> crearOperario(@RequestBody Operario operario){
        if (operario==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.operarioService.crearOperario(operario));

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Operario> actualizarOperario(@RequestBody Operario operario){
        try {
            return ResponseEntity.ok(this.operarioService.actualizarOperario(operario));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
