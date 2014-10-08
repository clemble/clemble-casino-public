package com.clemble.casino.lifecycle.configuration.rule.breach;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 8/30/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "breach")
abstract public class BreachPunishment {
}
