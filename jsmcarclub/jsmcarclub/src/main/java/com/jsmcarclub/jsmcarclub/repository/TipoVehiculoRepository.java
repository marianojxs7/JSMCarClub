
package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {

    boolean existsByNombre(String nombre);
}
