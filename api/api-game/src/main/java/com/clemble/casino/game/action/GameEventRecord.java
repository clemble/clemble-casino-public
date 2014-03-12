package com.clemble.casino.game.action;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.clemble.casino.event.GameEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

@Embeddable
public class GameEventRecord implements Serializable, Comparable<GameEventRecord> {

    /**
     * Generated
     */
    private static final long serialVersionUID = -7090751719767060121L;

    @Type(type = "com.clemble.casino.event.ClientEventHibernate")
    @Column(name = "EVENT", length = 2048)
    private GameEvent event;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    private Date created;

    public GameEventRecord(){
    }

    @JsonCreator
    public GameEventRecord(@JsonProperty("event") GameEvent event,
                           @JsonProperty("created") Date created) {
        this.event = event;
        this.created = created;
    }

    public GameEvent getEvent() {
        return event;
    }

    public GameEventRecord setEvent(GameEvent request) {
        this.event = request;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public GameEventRecord setCreated(Date created) {
        this.created = created;
        return this;
    }

    @Override
    public int compareTo(GameEventRecord o) {
        return created.compareTo(o.created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEventRecord madeMove = (GameEventRecord) o;

        if (created != null ? !created.equals(madeMove.created) : madeMove.created != null) return false;
        if (event != null ? !event.equals(madeMove.event) : madeMove.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = event != null ? event.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
