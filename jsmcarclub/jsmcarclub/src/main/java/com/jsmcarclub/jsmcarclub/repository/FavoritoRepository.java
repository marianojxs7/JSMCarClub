package com.jsmcarclub.jsmcarclub.repository;

import com.jsmcarclub.jsmcarclub.domain.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUsuarioId(Long usuarioId);

    Optional<Favorito> findByUsuarioIdAndVehiculoId(Long usuarioId, Long vehiculoId);

    void deleteByUsuarioIdAndVehiculoId(Long usuarioId, Long vehiculoId);
}
