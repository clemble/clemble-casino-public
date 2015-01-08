package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/3/14.
 */
@JsonTypeName("rule:bet:forbidden")
public class ForbiddenBetRule implements BetRule {

    final public static ForbiddenBetRule INSTANCE = new ForbiddenBetRule();

    private ForbiddenBetRule(){
    }

    @Override
    public boolean isValid(BetAction betAction) {
        return false;
    }

    @JsonCreator
    public static ForbiddenBetRule getInstance(@JsonProperty("betType") String betType) {
        return INSTANCE;
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == ForbiddenBetRule.class;
    }

}
