package com.clemble.casino.json;

import com.clemble.casino.event.ExpectedEvent;
import com.clemble.casino.rule.bet.BetRule;
import com.clemble.casino.rule.bet.FixedBetRule;
import com.clemble.casino.rule.bet.LimitedBetRule;
import com.clemble.casino.rule.bet.UnlimitedBetRule;
import com.clemble.casino.rule.breach.CountdownBreachPunishment;
import com.clemble.casino.rule.breach.LooseBreachPunishment;
import com.clemble.casino.rule.breach.PenaltyBreachPunishment;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CommonJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");
        module.registerSubtypes(new NamedType(ExpectedEvent.class, ExpectedEvent.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(CountdownBreachPunishment.class, CountdownBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PenaltyBreachPunishment.class, PenaltyBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LooseBreachPunishment.class, LooseBreachPunishment.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(BetRule.class, BetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(FixedBetRule.class, FixedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(LimitedBetRule.class, LimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(UnlimitedBetRule.class, UnlimitedBetRule.class.getAnnotation(JsonTypeName.class).value()));


        return module;
    }

}
