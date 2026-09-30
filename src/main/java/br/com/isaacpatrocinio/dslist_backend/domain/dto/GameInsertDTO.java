package br.com.isaacpatrocinio.dslist_backend.domain.dto;

public class GameInsertDTO extends GameDTO {

    private Long gameListId;

    public GameInsertDTO() {
    }

    public GameInsertDTO(GameDTO entity) {
        setId(entity.getId());
        setTitle(entity.getTitle());
        setGenre(entity.getGenre());
        setPlatforms(entity.getPlatforms());
        setImgUrl(entity.getImgUrl());
        setScore(entity.getScore());
        setYear(entity.getYear());
        setShortDescription(entity.getShortDescription());
        setLongDescription(entity.getLongDescription());
    }

    public Long getGameListId() {
        return gameListId;
    }

    public void setGameListId(Long gameListId) {
        this.gameListId = gameListId;
    }
}
