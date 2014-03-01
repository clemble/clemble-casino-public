package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameState extends GameProcessor<RoundGameRecord, Event>, Serializable {

    public RoundGameContext getContext();

    public GameUnit getRoot();

    public int getVersion();

}
