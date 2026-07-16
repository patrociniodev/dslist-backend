package br.com.isaacpatrocinio.dslist_backend.domain.dto;

import br.com.isaacpatrocinio.dslist_backend.domain.entities.Game;

public class Mapper {

    public static GameMinDTO toDTO(Game entity) {
        return new GameMinDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getYear(),
                entity.getImgUrl(),
                entity.getShortDescription()
        );
    }
}
