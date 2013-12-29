package com.clemble.casino.game.unit;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameUnit extends Serializable {

    public List<? extends GameUnit> getChildren();

}
