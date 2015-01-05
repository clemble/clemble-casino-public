package com.clemble.casino.lifecycle.configuration.rule.timeout;

/**
 * Created by mavarazy on 1/4/15.
 */

import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "total", value = TotalTimeoutCalculator.class),
    @JsonSubTypes.Type(name = "move", value = MoveTimeoutCalculator.class),
    @JsonSubTypes.Type(name = "eod", value = EODTimeoutCalculator.class)
})
public interface TimeoutCalculator {

    public long calculate(long moveStart);

    public long calculate(long moveStart, long timeSpent);

}
