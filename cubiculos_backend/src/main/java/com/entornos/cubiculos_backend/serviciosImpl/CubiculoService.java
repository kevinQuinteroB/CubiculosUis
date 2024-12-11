package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.exepciones.BadRequestException;
import com.entornos.cubiculos_backend.exepciones.ResourceNotFoundException;
import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.repositorios.ICubiculoRepository;
import com.entornos.cubiculos_backend.servicios.ICubiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CubiculoService implements ICubiculoService {
    @Autowired
    private ICubiculoRepository ICubiculoRepository;

    @Override
    public Cubiculo crearCubiculo(Cubiculo cubiculo) {
        //Verificar que no exista un cubiculo con el mismo numero, dado que sería incongruente
        if(ICubiculoRepository.findByNumeroCubiculo(cubiculo.getNumeroCubiculo()).isEmpty()) {
            return this.ICubiculoRepository.save(cubiculo);
        }else {
            throw new BadRequestException("Ya existe un cubiculo con el mismo numero");
        }
    }

    @Override
    public Cubiculo actualizarInformacionCubiculo(Cubiculo cubiculo) {
        // Verificar si el cubículo existe
        Optional<Cubiculo> cubiculoExistente = ICubiculoRepository.findById(cubiculo.getId());

        if (cubiculoExistente.isPresent()) {
            // Actualizar solo los campos que se deseen modificar
            Cubiculo cubiculoActualizado = cubiculoExistente.get();
            cubiculoActualizado.setCapacidad(cubiculo.getCapacidad());
            cubiculoActualizado.setPiso(cubiculo.getPiso());
            cubiculoActualizado.setDescripcion(cubiculo.getDescripcion());
            cubiculoActualizado.setIdEstadoCubiculo(cubiculo.getIdEstadoCubiculo());
            return ICubiculoRepository.save(cubiculoActualizado);

        } else {
            throw new ResourceNotFoundException("No existe un cubiculo con ID: " + cubiculo.getId());
        }
    }

    @Override
    public void eliminarCubiculo(Long id) {
        Optional<Cubiculo> cubiculoExistente = ICubiculoRepository.findById(id);

        if (cubiculoExistente.isPresent()) {
            ICubiculoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cubículo no encontrado con ID: " + id);
        }
    }

    @Override
    public List<Cubiculo> listarCubiculos(Long idPiso, Integer capacidad, String numeroCubiculo, Long idEstadoCubiculo) {
        return ICubiculoRepository.buscarCubiculos(idPiso, capacidad, numeroCubiculo, idEstadoCubiculo);
    }


}
