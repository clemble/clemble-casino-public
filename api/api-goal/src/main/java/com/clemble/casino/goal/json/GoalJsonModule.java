package com.clemble.casino.goal.json;

import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.construction.event.GoalInitiationCreatedEvent;
import com.clemble.casino.goal.rule.judge.JudgeRule;
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
        module.registerSubtypes(new NamedType(GoalInitiationCreatedEvent.class, GoalInitiationCreatedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(JudgeRule.class, JudgeRule.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
