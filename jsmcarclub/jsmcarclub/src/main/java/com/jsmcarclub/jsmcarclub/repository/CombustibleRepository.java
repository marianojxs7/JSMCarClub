
package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Combustible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombustibleRepository extends JpaRepository<Combustible, Long> {

    boolean existsByNombre(String nombre);
}
