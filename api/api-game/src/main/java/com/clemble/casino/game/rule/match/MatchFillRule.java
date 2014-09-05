package com.clemble.casino.game.rule.match;

import com.clemble.casino.game.rule.MatchRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("matchFillRule")
public enum MatchFillRule implements MatchRule {

    reminder,
    maxcommon,
    none;

}
