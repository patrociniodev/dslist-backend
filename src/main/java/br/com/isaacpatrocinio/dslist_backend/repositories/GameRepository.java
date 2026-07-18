package br.com.isaacpatrocinio.dslist_backend.repositories;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.projections.GameMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends
        JpaRepository<Game, Long> {

    @Query(value = """
            SELECT tb_game.id, tb_game.title, tb_game.game_year AS gameYear, tb_game.img_url as imgUrl, tb_game.short_description as shortDescription,tb_game.long_description as longDescription,tb_belonging.position
                FROM tb_game
                INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
                WHERE tb_belonging.game_list_id = :listId
                ORDER BY tb_belonging.position
            """
            , nativeQuery = true
    )
    List<GameMinProjection> searchGamesByList(Long listId);
}
