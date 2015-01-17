package com.clemble.casino.lifecycle.management.event.action.bet;

import com.clemble.casino.lifecycle.management.event.action.Action;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/17/15.
 */
@JsonTypeName(BidAction.JSON_TYPE)
public class BidAction implements Action {

    final public static String JSON_TYPE = "player:bid:action";

    final private static BidAction INSTANCE = new BidAction();

    @JsonCreator
    public static BidAction getInstance(@JsonProperty("type") String betType) {
        return INSTANCE;
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == BidAction.class;
    }

}
