package br.com.isaacpatrocinio.dslist_backend.services;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameListDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.Mapper;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameListRepository;
import br.com.isaacpatrocinio.dslist_backend.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;

    public GameListService(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll().stream().map(Mapper::gameListToDTO).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id) {
        return gameListRepository
                .findById(id)
                .map(Mapper::gameListToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Id doesn't exists"));
    }
}
