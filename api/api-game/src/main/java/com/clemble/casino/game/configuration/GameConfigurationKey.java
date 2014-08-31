package com.clemble.casino.game.configuration;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class GameConfigurationKey implements GameAware {

    /**
     * Generated 13/04/13
     */
    private static final long serialVersionUID = -4223872939422173928L;

    @Column(name = "GAME_NAME")
    @Enumerated(EnumType.STRING)
    private Game game;

    @Column(name = "SPECIFICATION_NAME")
    private String specificationName = "";

    public GameConfigurationKey() {
    }

    @JsonCreator
    public GameConfigurationKey(@JsonProperty("game") Game game, @JsonProperty("group") String group) {
        this.game = game;
        this.specificationName = group;
    }

    @Override
    public Game getGame() {
        return game;
    }

    public GameConfigurationKey setGame(Game game) {
        this.game = game;
        return this;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public GameConfigurationKey setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameConfigurationKey that = (GameConfigurationKey) o;

        if (game != that.game) return false;
        if (specificationName != null ? !specificationName.equals(that.specificationName) : that.specificationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (specificationName != null ? specificationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return game + ":" + specificationName;
    }

}
