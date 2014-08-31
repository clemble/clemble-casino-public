package com.clemble.casino.rule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 8/30/14.
 */
@JsonTypeName("loose")
public class LooseBreachPunishment extends BreachPunishment {

    final private static LooseBreachPunishment INSTANCE = new LooseBreachPunishment();

    @JsonCreator
    public static LooseBreachPunishment getInstance() {
        return INSTANCE;
    }

}
