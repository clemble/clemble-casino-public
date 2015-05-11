package com.clemble.casino.json;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.lifecycle.configuration.rule.bet.*;
import com.clemble.casino.lifecycle.configuration.rule.breach.DefaultBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.MoveTimeoutRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TotalTimeoutRule;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.TimeoutPunishmentAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BetOffAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BidAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.GiveUpAction;
import com.clemble.casino.lifecycle.configuration.rule.breach.CountdownBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.PenaltyBreachPunishment;
import com.clemble.casino.lifecycle.management.event.action.surrender.TimeoutSurrenderAction;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CommonJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");

        module.registerSubtypes(new NamedType(PlayerAction.class, PlayerAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerExpectedAction.class, PlayerExpectedAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(BetAction.class, BetAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(BidAction.class, BidAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerDefaultAction.class, PlayerDefaultAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(GiveUpAction.class, GiveUpAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(BetOffAction.class, BetOffAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(TimeoutPunishmentAction.class, TimeoutPunishmentAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(TimeoutSurrenderAction.class, TimeoutSurrenderAction.JSON_TYPE));

        module.registerSubtypes(new NamedType(CountdownBreachPunishment.class, CountdownBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PenaltyBreachPunishment.class, PenaltyBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LooseBreachPunishment.class, LooseBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(DefaultBreachPunishment.class, DefaultBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(MonoBetRule.class, MonoBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(FixedBetRule.class, FixedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(FixedBidRule.class, FixedBidRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LimitedBetRule.class, LimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(UnlimitedBetRule.class, UnlimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(ForbiddenBetRule.class, ForbiddenBetRule.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(TotalTimeoutRule.class, TotalTimeoutRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MoveTimeoutRule.class, MoveTimeoutRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MoveTimeRule.class, MoveTimeRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TotalTimeRule.class, TotalTimeRule.class.getAnnotation(JsonTypeName.class).value()));

        return module;
    }

}
