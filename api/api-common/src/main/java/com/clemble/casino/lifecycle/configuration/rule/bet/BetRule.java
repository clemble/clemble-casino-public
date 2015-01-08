package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

public interface BetRule extends ConfigurationRule {

    public boolean isValid(BetAction betAction);

}
