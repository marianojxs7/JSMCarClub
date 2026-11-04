
package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Transmision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmisionRepository extends JpaRepository<Transmision, Long> {

    boolean existsByNombre(String nombre);
}
