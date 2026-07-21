package br.com.isaacpatrocinio.dslist_backend.domain.dto;

public class GameInsertDTO extends GameDTO {

    private Long gameListId;

    public GameInsertDTO() {
        super();
    }

    public Long getGameListId() {
        return gameListId;
    }

    public void setGameListId(Long gameListId) {
        this.gameListId = gameListId;
    }
}
