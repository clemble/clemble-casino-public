package com.clemble.casino.lifecycle.configuration.rule.privacy;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("privacy")
public enum PrivacyRule implements ConfigurationRule {

    players,
    connections,
    everybody;

    final private static ConfigurationRuleOptions<PrivacyRule> OPTIONS =
        new ConfigurationRuleOptions<PrivacyRule>(players, connections, everybody, players);

    public ConfigurationRuleOptions<PrivacyRule> getOptions() {
        return OPTIONS;
    }

}
