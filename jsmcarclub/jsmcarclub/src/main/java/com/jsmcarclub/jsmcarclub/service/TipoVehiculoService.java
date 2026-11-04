
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.TipoVehiculo;
import com.jsmcarclub.jsmcarclub.repository.TipoVehiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoVehiculoService {

    private final TipoVehiculoRepository tipoRepository;

    public TipoVehiculoService(TipoVehiculoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<TipoVehiculo> listar() {
        return tipoRepository.findAll();
    }

    public TipoVehiculo buscarPorId(Long id) {
        return tipoRepository.findById(id).orElse(null);
    }

    public TipoVehiculo guardar(TipoVehiculo tipo) {
        return tipoRepository.save(tipo);
    }

    public void eliminar(Long id) {
        tipoRepository.deleteById(id);
    }
}
