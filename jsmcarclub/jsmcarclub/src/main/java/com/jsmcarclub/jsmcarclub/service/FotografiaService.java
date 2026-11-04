
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Fotografia;
import com.jsmcarclub.jsmcarclub.repository.FotografiaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FotografiaService {

    private final FotografiaRepository fotografiaRepository;

    public FotografiaService(FotografiaRepository fotografiaRepository) {
        this.fotografiaRepository = fotografiaRepository;
    }

    public List<Fotografia> listar() {
        return fotografiaRepository.findAll();
    }

    public List<Fotografia> listarPorVehiculo(Long vehiculoId) {
        return fotografiaRepository.findByVehiculoIdOrderByOrdenAsc(vehiculoId);
    }

    public Fotografia guardar(Fotografia fotografia) {
        return fotografiaRepository.save(fotografia);
    }

    public void eliminar(Long id) {
        fotografiaRepository.deleteById(id);
    }
}
