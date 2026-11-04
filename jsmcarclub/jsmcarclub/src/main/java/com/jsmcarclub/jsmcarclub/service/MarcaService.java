
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Marca;
import com.jsmcarclub.jsmcarclub.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}

