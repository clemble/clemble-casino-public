package com.clemble.casino.goal.lifecycle.configuration.rule.share;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/10/15.
 */
@JsonTypeName("rule:share")
public enum ShareRule implements GoalRule {

    none,
    facebook,
    twitter,
    google,
    vk;
}
