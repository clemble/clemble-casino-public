package com.clemble.casino.lifecycle.configuration.rule.breach;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 13/10/14.
 */
@JsonTypeName("default")
public class DefaultBreachPunishment extends BreachPunishment{

    final private static DefaultBreachPunishment INSTANCE = new DefaultBreachPunishment();

    @JsonCreator
    public static DefaultBreachPunishment getInstance() {
        return INSTANCE;
    }


    @Override
    public PlayerAction toBreachEvent(String key, String player) {
        return new PlayerAction(key, player, new PlayerDefaultAction());
    }

    // TODO this is a workaround for mongo serialization, used by springMongo, which is not general ObjectMapper, used in the system
    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == DefaultBreachPunishment.class;
    }

}
