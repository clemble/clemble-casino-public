package com.clemble.casino.json;

import com.clemble.casino.base.ExpectedEvent;
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

        return module;
    }

}
