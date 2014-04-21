package com.clemble.casino.game.rule.bet;

import com.clemble.casino.game.action.BetAction;
import com.clemble.casino.game.rule.RoundRule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bet")
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "betType")
public interface BetRule extends RoundRule {

    public boolean isValid(BetAction betEvent);

}