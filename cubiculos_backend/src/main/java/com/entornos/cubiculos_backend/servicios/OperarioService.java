package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Operario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OperarioService {
    List<Operario> getOperarios();
    Optional<Operario> getOperario(Long id);
    void borrarOperario(long id);
    Operario crearOperario(Operario operario);
    Operario actualizarOperario(Operario operario);
}
