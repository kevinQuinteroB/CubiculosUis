package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.modelos.Cubiculo;
import com.entornos.cubiculos_backend.repositorios.CubiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CubiculoService {
    @Autowired
    private CubiculoRepository cubiculoRepository;

    public Cubiculo insertarCubiculo(Cubiculo cubiculo) {
        return this.cubiculoRepository.save(cubiculo);
    }

    public List<Integer> getCubiculoPiso(){
        List<Cubiculo> lista = this.cubiculoRepository.findAll();
        List<Integer> cubiculoPiso = new ArrayList<Integer>();
        for (Cubiculo o : lista) {
            Integer piso =o.getPiso().getNumero_piso();
            cubiculoPiso.add(piso);
        }

        return cubiculoPiso;

    }
}
