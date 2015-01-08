package com.clemble.casino.game.lifecycle.configuration.rule.outcome;

import com.clemble.casino.game.lifecycle.configuration.rule.GameRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 23/12/13.
 */
@JsonTypeName("rule:game:won")
public enum WonRule implements GameRule {

    spent,
    price,
    owned;

}
