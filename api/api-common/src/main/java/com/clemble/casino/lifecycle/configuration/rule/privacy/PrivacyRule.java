package com.clemble.casino.lifecycle.configuration.rule.privacy;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rule:privacy")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrivacyRule implements ConfigurationRule {

    me,
    friends,
    world;

    public String getName() {
        return name();
    }

}
