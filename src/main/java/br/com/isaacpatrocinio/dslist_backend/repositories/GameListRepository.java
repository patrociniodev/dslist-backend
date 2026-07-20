package br.com.isaacpatrocinio.dslist_backend.repositories;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GameListRepository
        extends JpaRepository<GameList, Long> {

    @Modifying
    @Query(value = """
        UPDATE tb_belonging SET tb_belonging.position = :newPosition WHERE game_list_id = :listId AND game_id = :gameId
        """, nativeQuery = true)
    void updatePosition(Long listId, Long gameId, int newPosition);
}