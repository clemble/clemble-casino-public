package com.clemble.casino.lifecycle.configuration.rule.privacy;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeName("rule:privacy")
@JsonSerialize(using = PrivacyRuleFormat.PrivacyRuleSerializer.class)
@JsonDeserialize(using = PrivacyRuleFormat.PrivacyRuleDeserialization.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrivacyRule implements ConfigurationRule {

    me,
    friends,
    world;

    @JsonValue
    public String getName() {
        return name();
    }

}
