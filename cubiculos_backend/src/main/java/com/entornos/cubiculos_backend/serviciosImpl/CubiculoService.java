package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.exepciones.BadRequestException;
import com.entornos.cubiculos_backend.exepciones.ResourceNotFoundException;
import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.repositorios.CubiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CubiculoService {
    @Autowired
    private CubiculoRepository cubiculoRepository;

    public Cubiculo crearCubiculo(Cubiculo cubiculo) {
        //Verificar que no exista un cubiculo con el mismo numero, dado que sería incongruente
        if(cubiculoRepository.findByNumeroCubiculo(cubiculo.getNumeroCubiculo()).isEmpty()) {
            return this.cubiculoRepository.save(cubiculo);
        }else {
            throw new BadRequestException("Ya existe un cubiculo con el mismo numero");
        }
    }

    public Cubiculo actualizarInformacionCubiculo(Cubiculo cubiculo) {
        // Verificar si el cubículo existe
        Optional<Cubiculo> cubiculoExistente = cubiculoRepository.findById(cubiculo.getId());

        if (cubiculoExistente.isPresent()) {
            // Actualizar solo los campos que se deseen modificar
            Cubiculo cubiculoActualizado = cubiculoExistente.get();
            cubiculoActualizado.setCapacidad(cubiculo.getCapacidad());
            cubiculoActualizado.setPiso(cubiculo.getPiso());
            cubiculoActualizado.setDescripcion(cubiculo.getDescripcion());
            cubiculoActualizado.setIdEstadoCubiculo(cubiculo.getIdEstadoCubiculo());
            return cubiculoRepository.save(cubiculoActualizado);

        } else {
            throw new ResourceNotFoundException("No existe un cubiculo con ID: " + cubiculo.getId());
        }
    }

    public void eliminarCubiculo(Long id) {
        Optional<Cubiculo> cubiculoExistente = cubiculoRepository.findById(id);

        if (cubiculoExistente.isPresent()) {
            cubiculoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cubículo no encontrado con ID: " + id);
        }
    }

    public List<Cubiculo> listarCubiculos(Long idPiso, Integer capacidad, String numeroCubiculo, Long idEstadoCubiculo) {
        return cubiculoRepository.buscarCubiculos(idPiso, capacidad, numeroCubiculo, idEstadoCubiculo);
    }


}
