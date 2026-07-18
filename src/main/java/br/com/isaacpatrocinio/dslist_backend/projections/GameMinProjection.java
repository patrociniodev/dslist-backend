package br.com.isaacpatrocinio.dslist_backend.projections;

public interface GameMinProjection {

    Long getId();
    String getTitle();
    Integer getGameYear();
    String getImgUrl();
    String getShortDescription();
    String getLongDescription();
    Integer getPosition();
}
