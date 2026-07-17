package br.com.isaacpatrocinio.dslist_backend.repositories;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository
        extends JpaRepository<GameList, Long> {
}