package com.clemble.casino.game.rule.construct;

import com.clemble.casino.game.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("privacy")
public enum PrivacyRule implements ConfigurationRule {

    players,
    everybody;

    final public static PrivacyRule DEFAULT = PrivacyRule.players;

}
