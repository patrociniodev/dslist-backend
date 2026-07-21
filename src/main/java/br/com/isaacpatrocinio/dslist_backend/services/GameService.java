package br.com.isaacpatrocinio.dslist_backend.services;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameInsertDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.Mapper;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Belonging;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.GameList;
import br.com.isaacpatrocinio.dslist_backend.projections.GameMinProjection;
import br.com.isaacpatrocinio.dslist_backend.repositories.BelongingRepository;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameListRepository;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameRepository;
import br.com.isaacpatrocinio.dslist_backend.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameListRepository gameListRepository;
    private final BelongingRepository belongingRepository;

    public GameService(GameRepository gameRepository, GameListRepository gameListRepository, BelongingRepository belongingRepository) {
        this.gameRepository = gameRepository;
        this.gameListRepository = gameListRepository;
        this.belongingRepository = belongingRepository;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(Mapper::gameToMinDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        return gameRepository.findById(id)
                .map(Mapper::gameToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Id doesn't exists"));
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        return gameRepository.searchGamesByList(listId)
                .stream()
                .map(Mapper::projectionToMinDTO)
                .toList();
    }

    @Transactional
    public GameDTO insert(GameInsertDTO entityDTO) {
        Game obj = Mapper.gameFromDTO(entityDTO);
        Game saved = gameRepository.save(obj);

        if (entityDTO.getGameListId() != null) {
            GameList gameList = gameListRepository.findById(entityDTO.getGameListId())
                    .orElseThrow(() -> new ResourceNotFoundException("GameList id doesn't exist"));
            long position = belongingRepository.countByGameListId(entityDTO.getGameListId());
            belongingRepository.save(new Belonging(saved, gameList, (int) position));
        }

        return Mapper.gameToDTO(saved);
    }
}
