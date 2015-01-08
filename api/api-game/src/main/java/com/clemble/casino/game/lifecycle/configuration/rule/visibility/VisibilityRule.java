package com.clemble.casino.game.lifecycle.configuration.rule.visibility;

import com.clemble.casino.game.lifecycle.configuration.rule.GameRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rule:game:visibility")
public enum VisibilityRule implements GameRule {

    visible,
    hidden;

}
