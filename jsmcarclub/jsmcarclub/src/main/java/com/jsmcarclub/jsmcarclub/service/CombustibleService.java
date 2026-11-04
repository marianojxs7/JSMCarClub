
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Combustible;
import com.jsmcarclub.jsmcarclub.repository.CombustibleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CombustibleService {

    private final CombustibleRepository combustibleRepository;

    public CombustibleService(CombustibleRepository combustibleRepository) {
        this.combustibleRepository = combustibleRepository;
    }

    public List<Combustible> listar() {
        return combustibleRepository.findAll();
    }

    public Combustible buscarPorId(Long id) {
        return combustibleRepository.findById(id).orElse(null);
    }

    public Combustible guardar(Combustible combustible) {
        return combustibleRepository.save(combustible);
    }

    public void eliminar(Long id) {
        combustibleRepository.deleteById(id);
    }
}
