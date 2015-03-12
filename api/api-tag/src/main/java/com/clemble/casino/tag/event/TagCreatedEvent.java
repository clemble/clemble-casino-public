package com.clemble.casino.tag.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 2/3/15.
 */
@JsonTypeName(TagCreatedEvent.JSON_TYPE)
public class TagCreatedEvent implements TagEvent {

    final public static String JSON_TYPE = "tag:created";

    final private String tag;

    @JsonCreator
    public TagCreatedEvent(@JsonProperty("tag") String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagCreatedEvent)) return false;

        TagCreatedEvent that = (TagCreatedEvent) o;

        if (!tag.equals(that.tag)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

}
