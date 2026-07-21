package br.com.isaacpatrocinio.dslist_backend.repositories;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Belonging;
import br.com.isaacpatrocinio.dslist_backend.domain.pk.BelongingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {

    @Query("SELECT COUNT(b) FROM Belonging b WHERE b.id.gameList.id = :listId")
    long countByGameListId(Long listId);
}
