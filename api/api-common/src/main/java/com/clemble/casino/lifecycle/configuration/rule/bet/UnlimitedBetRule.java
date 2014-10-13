package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("unlimited")
public class UnlimitedBetRule implements BetRule {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = 6788161410535376939L;

    final public static UnlimitedBetRule INSTANCE = new UnlimitedBetRule();

    @JsonIgnore
    private UnlimitedBetRule() {
    }

    @Override
    public boolean isValid(BetAction betAction) {
        return true;
    }

    // This constructor is a workaround for Jackson deserializer
    // There can't be Creator without at least one element
    @JsonCreator
    public static UnlimitedBetRule getInstance(@JsonProperty("betType") String betType) {
        return INSTANCE;
    }

    // TODO this is a workaround for mongo serialization, used by springMongo, which is not general ObjectMapper, used in the system
    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == UnlimitedBetRule.class;
    }

}
