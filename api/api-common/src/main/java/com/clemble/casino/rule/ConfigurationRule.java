package com.clemble.casino.rule;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 9/5/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "rule")
public interface ConfigurationRule {
}
