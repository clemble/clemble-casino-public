package com.clemble.casino.lifecycle.configuration.rule.breach;

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

    // TODO this is a workaround for mongo serialization, used by springMongo, which is not general ObjectMapper, used in the system
    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == LooseBreachPunishment.class;
    }

}
