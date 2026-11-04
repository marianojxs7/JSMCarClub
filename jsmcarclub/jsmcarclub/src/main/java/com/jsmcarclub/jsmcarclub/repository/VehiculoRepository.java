package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByActivoTrue();

    List<Vehiculo> findByPremiumTrueAndActivoTrue();

    List<Vehiculo> findByMarcaIdAndActivoTrue(Long marcaId);

    List<Vehiculo> findByTipoVehiculoIdAndActivoTrue(Long tipoId);

    List<Vehiculo> findByPrecioBetweenAndActivoTrue(Double minimo, Double maximo);
}
