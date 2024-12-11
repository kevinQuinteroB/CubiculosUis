package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.modelos.Operario;
import com.entornos.cubiculos_backend.repositorios.OperarioRepository;
import com.entornos.cubiculos_backend.servicios.OperarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OperarioServiceImpl implements OperarioService {
    @Autowired
    private OperarioRepository operarioRepository;

    public List<Operario> getOperarios() {
        return this.operarioRepository.findAll();
    }

    public Optional<Operario> getOperario(Long id) {
        return this.operarioRepository.findById(id);
    }

    public void borrarOperario(long id) {
        this.operarioRepository.deleteById(id);
    }

    public Operario crearOperario(Operario operario) {
        return this.operarioRepository.save(operario);
    }

    public Operario actualizarOperario(Operario operario) {
        Operario operarioActual = getOperario(operario.getId()).
                orElseThrow(()->new NoSuchElementException("No se encontró el operario"));
        operarioActual.setNombre(operario.getNombre());
        operarioActual.setDocumento(operario.getDocumento());

        return this.operarioRepository.save(operarioActual);
    }

}
