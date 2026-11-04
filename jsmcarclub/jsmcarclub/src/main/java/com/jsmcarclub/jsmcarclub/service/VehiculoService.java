
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Vehiculo;
import com.jsmcarclub.jsmcarclub.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    public List<Vehiculo> listarActivos() {
        return vehiculoRepository.findByActivoTrue();
    }

    public List<Vehiculo> listarPremium() {
        return vehiculoRepository.findByPremiumTrueAndActivoTrue();
    }

    public Vehiculo buscarPorId(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> filtrarPorMarca(Long marcaId) {
        return vehiculoRepository.findByMarcaIdAndActivoTrue(marcaId);
    }

    public List<Vehiculo> filtrarPorTipo(Long tipoId) {
        return vehiculoRepository.findByTipoVehiculoIdAndActivoTrue(tipoId);
    }

    public List<Vehiculo> filtrarPorPrecio(Double minimo, Double maximo) {
        return vehiculoRepository.findByPrecioBetweenAndActivoTrue(minimo, maximo);
    }

    public void eliminar(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
