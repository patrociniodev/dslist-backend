package br.com.isaacpatrocinio.dslist_backend.controllers;

import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameListDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.GameMinDTO;
import br.com.isaacpatrocinio.dslist_backend.domain.dto.ReplacementDTO;
import br.com.isaacpatrocinio.dslist_backend.services.GameListService;
import br.com.isaacpatrocinio.dslist_backend.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    public final GameListService gameListService;

    public final GameService gameService;

    public GameListController(GameListService gameListService, GameService gameService) {
        this.gameListService = gameListService;
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findGamesByList(@PathVariable Long listId) {
        return gameService.findByList(listId);
    }

    @PostMapping("{listId}/replacement")
    public ResponseEntity<Void> moveGame(@PathVariable Long listId, @RequestBody ReplacementDTO dto) {
        gameListService.moveGame(listId, dto.getSourceIndex(), dto.getDestinationIndex());
        return ResponseEntity.ok().build();
    }
}
