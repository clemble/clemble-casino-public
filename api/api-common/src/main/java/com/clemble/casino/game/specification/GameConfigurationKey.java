package com.clemble.casino.game.specification;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specificationName == null) ? 0 : specificationName.hashCode());
        result = prime * result + ((game == null) ? 0 : game.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameConfigurationKey other = (GameConfigurationKey) obj;
        if (specificationName == null) {
            if (other.specificationName != null)
                return false;
        } else if (!specificationName.equals(other.specificationName))
            return false;
        if (game == null) {
            if (other.game != null)
                return false;
        } else if (!game.equals(other.game))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return game + ":" + specificationName;
    }

}
