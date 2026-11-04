package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByVehiculoId(Long vehiculoId);

    List<Transaccion> findByEstado(String estado);
}
