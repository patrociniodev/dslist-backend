package br.com.isaacpatrocinio.dslist_backend.domain.dto;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;
import org.springframework.beans.BeanUtils;

public class Mapper {

    public static GameMinDTO toMinDTO(Game entity) {
        GameMinDTO minDTO = new GameMinDTO();
        BeanUtils.copyProperties(entity, minDTO);
        return minDTO;
    }

    public static GameDTO toDTO(Game entity) {
        GameDTO gameDTO = new GameDTO();
        BeanUtils.copyProperties(entity, gameDTO);
        return gameDTO;
    }
}
