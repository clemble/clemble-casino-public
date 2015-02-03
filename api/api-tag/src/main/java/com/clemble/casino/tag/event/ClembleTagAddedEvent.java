package com.clemble.casino.tag.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 2/3/15.
 */
@JsonTypeName(ClembleTagAddedEvent.JSON_TYPE)
public class ClembleTagAddedEvent implements ClembleTagEvent {

    final public static String JSON_TYPE = "tag:added";

    final private String tag;

    @JsonCreator
    public ClembleTagAddedEvent(@JsonProperty("tag") String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleTagAddedEvent)) return false;

        ClembleTagAddedEvent that = (ClembleTagAddedEvent) o;

        if (!tag.equals(that.tag)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

}
