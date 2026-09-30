package br.com.isaacpatrocinio.dslist_backend.controllers;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameInsertDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.repositories.GameRepository;
import br.com.isaacpatrocinio.dslist_backend.services.GameService;
import br.com.isaacpatrocinio.dslist_backend.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    public final GameService gameService;
    private final GameRepository gameRepository;

    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @GetMapping(value = "/{gameId}")
    public GameDTO findById(@PathVariable Long gameId) {
        return gameService.findById(gameId);
    }

    @PostMapping
    public ResponseEntity<GameDTO> insert(
            @RequestBody GameInsertDTO entityDTO,
            HttpServletRequest request) {
        GameDTO insertedGame = gameService.insert(entityDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(request.getRequestURI())
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).body(insertedGame);
    }

    @PutMapping(value = "/{gameId}")
    public ResponseEntity<GameDTO> update(
            @PathVariable Long gameId,
            @RequestBody GameInsertDTO obj) {
        return ResponseEntity.ok().body(gameService.update(gameId, obj));
    }

    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<Void> delete(@PathVariable Long gameId) {
        if(!gameRepository.existsById(gameId)) {
            throw new ResourceNotFoundException("Id doesn't exists");
        }
        gameService.delete(gameId);
        return ResponseEntity.noContent().build();
    }
}
