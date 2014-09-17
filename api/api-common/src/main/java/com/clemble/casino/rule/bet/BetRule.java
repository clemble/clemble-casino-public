package com.clemble.casino.rule.bet;

import com.clemble.casino.event.bet.PlayerBetAction;
import com.clemble.casino.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bet")
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "betType")
public interface BetRule extends ConfigurationRule {

    public boolean isValid(PlayerBetAction playerBetAction);

}
