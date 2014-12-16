package com.clemble.casino.goal.lifecycle.configuration.rule.reminder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/16/14.
 */
public class NoReminderRule implements ReminderRule {

    final public static NoReminderRule INSTANCE = new NoReminderRule();

    private NoReminderRule() {
    }

    // This constructor is a workaround for Jackson deserializer
    // There can't be Creator without at least one element
    @JsonCreator
    public static NoReminderRule getInstance(@JsonProperty("type") String betType) {
        return INSTANCE;
    }

}
