package com.clemble.casino.goal.lifecycle.configuration.rule.remind;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/10/14.
 */
public interface RemindRule extends GoalRule {

    public long getReminder();

}
