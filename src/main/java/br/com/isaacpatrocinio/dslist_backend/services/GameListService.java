package br.com.isaacpatrocinio.dslist_backend.services;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameListDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.Mapper;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.projections.GameMinProjection;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameListRepository;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameRepository;
import br.com.isaacpatrocinio.dslist_backend.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;
    private final GameRepository gameRepository;

    public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll()
                .stream()
                .map(Mapper::gameListToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id) {
        return gameListRepository
                .findById(id)
                .map(Mapper::gameListToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Id doesn't exists"));
    }

    @Transactional
    public void moveGame(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchGamesByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);


        for (int i = min; i <= max ; i++) {
            gameListRepository.updatePosition(listId, list.get(i).getId(), i);
        }
    }
}
