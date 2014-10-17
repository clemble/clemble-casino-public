package com.clemble.casino.goal.json;

import com.clemble.casino.goal.event.action.GoalStatusUpdateAction;
import com.clemble.casino.goal.lifecycle.configuration.rule.parts.GoalPartsRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.start.GoalStartRule;
import com.clemble.casino.goal.lifecycle.construction.event.GoalConstructionCompleteEvent;
import com.clemble.casino.goal.lifecycle.initiation.event.GoalInitiationCreatedEvent;
import com.clemble.casino.goal.lifecycle.configuration.rule.judge.JudgeRule;
import com.clemble.casino.goal.lifecycle.management.event.GoalChangedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalEndedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalStartedEvent;
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
        module.registerSubtypes(new NamedType(GoalInitiationCreatedEvent.class, GoalInitiationCreatedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(JudgeRule.class, JudgeRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GoalStartRule.class, GoalStartRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GoalPartsRule.class, GoalPartsRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GoalStartedEvent.class, GoalStartedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalEndedEvent.class, GoalEndedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalConstructionCompleteEvent.class, GoalConstructionCompleteEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalStatusUpdateAction.class, GoalStatusUpdateAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalChangedEvent.class, GoalChangedEvent.JSON_TYPE));
        return module;
    }

}
