package br.com.isaacpatrocinio.dslist_backend.services;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.Mapper;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameMinDTO> findAll() {
        List<Game> list = gameRepository.findAll();
        return list.stream().map(Mapper::toMinDTO).toList();
    }

    public GameDTO findById(Long id) {
        return gameRepository.findById(id)
                .map(Mapper::toDTO)
                .get();
    }
}
