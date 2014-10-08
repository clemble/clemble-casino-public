package com.clemble.casino.lifecycle.record;

import com.clemble.casino.event.Event;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mavarazy on 9/20/14.
 */
public class EventRecord implements Comparable<EventRecord> {

    final private Event event;
    final private Date created;

    @JsonCreator
    public EventRecord(@JsonProperty("event") Event event, @JsonProperty("created") Date created) {
        this.event = event;
        this.created = created;
    }

    public Event getEvent() {
        return event;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public int compareTo(EventRecord o) {
        return created.compareTo(o.created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventRecord that = (EventRecord) o;

        if (!created.equals(that.created)) return false;
        if (!event.equals(that.event)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }


}
