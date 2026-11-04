
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Transmision;
import com.jsmcarclub.jsmcarclub.repository.TransmisionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransmisionService {

    private final TransmisionRepository transmisionRepository;

    public TransmisionService(TransmisionRepository transmisionRepository) {
        this.transmisionRepository = transmisionRepository;
    }

    public List<Transmision> listar() {
        return transmisionRepository.findAll();
    }

    public Transmision buscarPorId(Long id) {
        return transmisionRepository.findById(id).orElse(null);
    }

    public Transmision guardar(Transmision transmision) {
        return transmisionRepository.save(transmision);
    }

    public void eliminar(Long id) {
        transmisionRepository.deleteById(id);
    }
}

