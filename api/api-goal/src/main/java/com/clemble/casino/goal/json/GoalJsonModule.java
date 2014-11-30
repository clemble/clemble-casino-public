package com.clemble.casino.goal.json;

import com.clemble.casino.goal.event.action.GoalReachedAction;
import com.clemble.casino.goal.event.action.GoalStatusUpdateAction;
import com.clemble.casino.goal.lifecycle.construction.event.GoalConstructionCompleteEvent;
import com.clemble.casino.goal.lifecycle.initiation.event.GoalInitiationChangedEvent;
import com.clemble.casino.goal.lifecycle.initiation.event.GoalInitiationCompleteEvent;
import com.clemble.casino.goal.lifecycle.initiation.event.GoalInitiationCreatedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalChangedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalEndedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalStartedEvent;
import com.clemble.casino.goal.lifecycle.record.event.GoalRecordCreatedEvent;
import com.clemble.casino.goal.post.*;
import com.clemble.casino.json.ClembleJsonModule;
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
        module.registerSubtypes(new NamedType(GoalInitiationCompleteEvent.class, GoalInitiationCompleteEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalInitiationChangedEvent.class, GoalInitiationChangedEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(GoalStartedEvent.class, GoalStartedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalChangedEvent.class, GoalChangedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalEndedEvent.class, GoalEndedEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(GoalConstructionCompleteEvent.class, GoalConstructionCompleteEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(GoalRecordCreatedEvent.class, GoalRecordCreatedEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(GoalStatusUpdateAction.class, GoalStatusUpdateAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalReachedAction.class, GoalReachedAction.JSON_TYPE));

        module.registerSubtypes(new NamedType(GoalCreatedPost.class, GoalCreatedPost.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalStartedPost.class, GoalStartedPost.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalBidPost.class, GoalBidPost.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalUpdatedPost.class, GoalUpdatedPost.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalReachedPost.class, GoalReachedPost.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalMissedPost.class, GoalMissedPost.JSON_TYPE));

        return module;
    }

}
