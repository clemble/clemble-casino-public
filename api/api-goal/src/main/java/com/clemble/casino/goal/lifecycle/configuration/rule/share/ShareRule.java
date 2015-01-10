package com.clemble.casino.goal.lifecycle.configuration.rule.share;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/10/15.
 */
@JsonTypeName("rule:share")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ShareRule implements GoalRule {

    none,
    facebook,
    twitter,
    vk,
    google;

    public String getName() {
        return name();
    }

}
