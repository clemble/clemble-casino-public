package com.clemble.casino.goal.json;

import com.clemble.casino.event.DefaultPlayerEvent;
import com.clemble.casino.event.bet.BetEvent;
import com.clemble.casino.event.surrender.GiveUpEvent;
import com.clemble.casino.event.surrender.MoveTimeoutSurrenderEvent;
import com.clemble.casino.event.surrender.TotalTimeoutSurrenderEvent;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.construction.event.GoalInitiatedEvent;
import com.clemble.casino.json.ClembleJsonModule;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by mavarazy on 9/6/14.
 */
public class GoalJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Goal");
        module.registerSubtypes(new NamedType(GoalConfiguration.class, GoalConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GoalInitiatedEvent.class, GoalInitiatedEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
