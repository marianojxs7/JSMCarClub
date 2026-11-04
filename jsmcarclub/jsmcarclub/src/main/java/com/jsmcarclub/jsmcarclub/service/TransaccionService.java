
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Transaccion;
import com.jsmcarclub.jsmcarclub.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<Transaccion> listar() {
        return transaccionRepository.findAll();
    }

    public Transaccion buscarPorId(Long id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    public List<Transaccion> listarPorVehiculo(Long vehiculoId) {
        return transaccionRepository.findByVehiculoId(vehiculoId);
    }

    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> listarPorEstado(String estado) {
        return transaccionRepository.findByEstado(estado);
    }
}

