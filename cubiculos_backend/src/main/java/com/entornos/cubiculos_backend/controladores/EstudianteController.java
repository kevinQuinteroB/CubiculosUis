package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.modelos.Estudiante;
import com.entornos.cubiculos_backend.servicios.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Estudiante>> listar(){
        if (this.estudianteService.getEstudiantes().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.estudianteService.getEstudiantes());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> borrarEstudiante(@RequestParam(name = "id") long id){
        if(this.estudianteService.getEstudiante(id).isEmpty()) return ResponseEntity.noContent().build();
        this.estudianteService.borrarEstudiante(id);
        return ResponseEntity.ok("Estudiante borrado exitosamente");
    }

    @PostMapping("/crear")
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante){
        if (estudiante==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.estudianteService.crearEstudiante(estudiante));

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Estudiante> actualizarEstudiante(@RequestBody Estudiante estudiante){
        try {
            return ResponseEntity.ok(this.estudianteService.actualizarEstudiante(estudiante));
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();

        }catch (Exception e){

            return ResponseEntity.badRequest().build();
        }

    }


}
