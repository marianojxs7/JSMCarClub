package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {

    List<Fotografia> findByVehiculoIdOrderByOrdenAsc(Long vehiculoId);
}
