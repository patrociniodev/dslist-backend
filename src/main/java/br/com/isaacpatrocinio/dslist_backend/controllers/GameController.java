package br.com.isaacpatrocinio.dslist_backend.controllers;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameInsertDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.services.GameService;
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

    public GameController(GameService gameService) {
        this.gameService = gameService;
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
}
