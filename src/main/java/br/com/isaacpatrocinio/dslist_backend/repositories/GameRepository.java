package br.com.isaacpatrocinio.dslist_backend.repositories;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends
        JpaRepository<Game, Long> {
}
