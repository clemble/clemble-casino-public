package com.clemble.casino.lifecycle.configuration.rule.timeout;

/**
 * Created by mavarazy on 1/4/15.
 */

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.joda.time.DateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "total", value = TotalTimeoutCalculatorByLimit.class),
    @JsonSubTypes.Type(name = "eod", value = TotalTimeoutCalculatorByEOD.class)
})
public interface TotalTimeoutCalculator {

    DateTime calculate(DateTime startTime);

    DateTime calculate(GoalTimeSpanAware timeframe);

}
