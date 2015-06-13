package com.clemble.casino.lifecycle.configuration.rule.timeout;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 1/4/15.
 */@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "limit", value = MoveTimeoutCalculatorByLimit.class),
    @JsonSubTypes.Type(name = "eod", value = MoveTimeoutCalculatorByEOD.class),
})
public interface MoveTimeoutCalculator {

    DateTime calculate(DateTime lastUpdate);

    DateTime calculate(GoalTimeSpanAware timeSpanAware);

}
