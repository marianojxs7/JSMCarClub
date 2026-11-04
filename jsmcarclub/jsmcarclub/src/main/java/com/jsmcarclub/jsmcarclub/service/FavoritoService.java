
package com.jsmcarclub.jsmcarclub.service;

import com.jsmcarclub.jsmcarclub.domain.Favorito;
import com.jsmcarclub.jsmcarclub.repository.FavoritoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public List<Favorito> listarPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    public Favorito guardar(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public void eliminar(Long usuarioId, Long vehiculoId) {
        favoritoRepository.deleteByUsuarioIdAndVehiculoId(
            usuarioId, vehiculoId
        );
    }

    public boolean esFavorito(Long usuarioId, Long vehiculoId) {
        return favoritoRepository
            .findByUsuarioIdAndVehiculoId(usuarioId, vehiculoId)
            .isPresent();
    }
}
