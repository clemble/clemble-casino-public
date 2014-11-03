package com.clemble.casino.lifecycle.configuration.rule.privacy;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRuleOptions;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PrivacyRule implements ConfigurationRule {

    me,
    friends,
    world;

    public String getName() {
        return name();
    }

}
