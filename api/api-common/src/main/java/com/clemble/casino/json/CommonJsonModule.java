package com.clemble.casino.json;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.lifecycle.configuration.rule.breach.DefaultBreachPunishment;
import com.clemble.casino.lifecycle.management.event.action.TimeoutPunishmentAction;
import com.clemble.casino.lifecycle.management.event.action.bet.PlayerBetAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.GiveUpAction;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.FixedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.LimitedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.UnlimitedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.breach.CountdownBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.PenaltyBreachPunishment;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CommonJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");

        module.registerSubtypes(new NamedType(PlayerExpectedAction.class, PlayerExpectedAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerBetAction.class, PlayerBetAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerDefaultAction.class, PlayerDefaultAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(GiveUpAction.class, GiveUpAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(TimeoutPunishmentAction.class, TimeoutPunishmentAction.JSON_TYPE));

        module.registerSubtypes(new NamedType(CountdownBreachPunishment.class, CountdownBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PenaltyBreachPunishment.class, PenaltyBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LooseBreachPunishment.class, LooseBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(DefaultBreachPunishment.class, DefaultBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(BetRule.class, BetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(FixedBetRule.class, FixedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LimitedBetRule.class, LimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(UnlimitedBetRule.class, UnlimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));

        return module;
    }

}
