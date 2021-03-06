package com.clemble.casino.lifecycle.record;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.event.Event;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by mavarazy on 9/20/14.
 */
public class EventRecord implements Comparable<EventRecord>, CreatedAware {

    final private Event event;
    final private DateTime created;

    @JsonCreator
    public EventRecord(@JsonProperty("event") Event event, @JsonProperty("created") DateTime created) {
        this.event = event;
        this.created = created;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public int compareTo(EventRecord o) {
        return o.created.compareTo(created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventRecord that = (EventRecord) o;

        if (!created.equals(that.created)) return false;
        return event.equals(that.event);

    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "record:" + created + ":" + event;
    }

}
