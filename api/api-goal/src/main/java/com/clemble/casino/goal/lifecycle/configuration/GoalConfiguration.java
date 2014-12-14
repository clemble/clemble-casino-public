package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.configuration.BetConfigurationConvertible;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.EmailReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.PhoneReminderRule;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 8/26/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "short", value = ShortGoalConfiguration.class),
    @JsonSubTypes.Type(name = "long", value = LongGoalConfiguration.class)
})
public interface GoalConfiguration extends Configuration, GoalConfigurationKeyAware, BetConfigurationConvertible {

    public Bid getBid();

    public BetRule getBetRule();

    public MoveTimeRule getMoveTimeRule();

    public TotalTimeRule getTotalTimeRule();

    public EmailReminderRule getEmailReminderRule();

    public PhoneReminderRule getPhoneReminderRule();

}
