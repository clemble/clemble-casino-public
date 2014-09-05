package com.clemble.casino.rule.privacy;

import com.clemble.casino.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("privacy")
public enum PrivacyRule implements ConfigurationRule {

    players,
    connections,
    everybody;;

    final public static PrivacyRule DEFAULT = PrivacyRule.players;

}
