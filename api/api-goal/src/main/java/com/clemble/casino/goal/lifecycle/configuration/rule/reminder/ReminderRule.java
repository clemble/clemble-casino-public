package com.clemble.casino.goal.lifecycle.configuration.rule.reminder;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 12/10/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "basic", value = BasicReminderRule.class),
    @JsonSubTypes.Type(name = "no", value = NoReminderRule.class)
})
public interface ReminderRule extends GoalRule {

}
