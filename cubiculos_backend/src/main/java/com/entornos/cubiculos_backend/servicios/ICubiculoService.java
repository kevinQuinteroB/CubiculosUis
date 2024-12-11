package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICubiculoService {

    Cubiculo crearCubiculo(Cubiculo cubiculo);

    Cubiculo actualizarInformacionCubiculo(Cubiculo cubiculo);

    void eliminarCubiculo(Long id);

    List<Cubiculo> listarCubiculos(Long idPiso, Integer capacidad, String numeroCubiculo, Long idEstadoCubiculo);

}
