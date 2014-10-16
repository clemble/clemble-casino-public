package com.clemble.casino.lifecycle.management.outcome;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "outcome")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "no", value = NoOutcome.class),
    @JsonSubTypes.Type(name = "draw", value = DrawOutcome.class),
    @JsonSubTypes.Type(name = "won", value = PlayerWonOutcome.class),
    @JsonSubTypes.Type(name = "lost", value = PlayerLostOutcome.class)
})
abstract public class Outcome implements Serializable {

    private static final long serialVersionUID = -7763234573172626298L;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

}
