package br.com.isaacpatrocinio.dslist_backend.domain.dto;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import br.com.isaacpatrocinio.dslist_backend.domain.entities.GameList;
import br.com.isaacpatrocinio.dslist_backend.projections.GameMinProjection;
import org.springframework.beans.BeanUtils;

public class Mapper {

    public static GameMinDTO gameToMinDTO(Game entity) {
        GameMinDTO minDTO = new GameMinDTO();
        BeanUtils.copyProperties(entity, minDTO);
        return minDTO;
    }

    public static GameDTO gameToDTO(Game entity) {
        GameDTO gameDTO = new GameDTO();
        BeanUtils.copyProperties(entity, gameDTO);
        return gameDTO;
    }

    public static GameListDTO gameListToDTO(GameList entity) {
        GameListDTO gameListDTO = new GameListDTO();
        BeanUtils.copyProperties(entity, gameListDTO);
        return gameListDTO;
    }

    public static GameMinDTO projectionToMinDTO(GameMinProjection projection) {
        return new GameMinDTO(
                projection.getId(),
                projection.getTitle(),
                projection.getGameYear(),
                projection.getImgUrl(),
                projection.getShortDescription()
        );
    }
}
