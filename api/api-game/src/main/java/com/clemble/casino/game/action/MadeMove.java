package com.clemble.casino.game.action;

import java.io.Serializable;
import java.util.Collection;
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
public class MadeMove implements Serializable, Comparable<MadeMove> {

    /**
     * 
     */
    private static final long serialVersionUID = -7090751719767060121L;

    @Type(type = "com.clemble.casino.event.ClientEventHibernate")
    @Column(name = "REQUEST", length = 2048)
    private GameEvent request;

    @Type(type = "com.clemble.casino.event.ClientEventHibernate")
    @Column(name = "RESPONSE", length = 2048)
    private GameEvent response;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    private Date created;

    public MadeMove(){
    }

    @JsonCreator
    public MadeMove(@JsonProperty("request") GameEvent request,
                    @JsonProperty("response") GameEvent response,
                    @JsonProperty("created") Date created) {
        this.request = request;
        this.response = response;
        this.created = created;
    }

    public GameEvent getRequest() {
        return request;
    }

    public MadeMove setRequest(GameEvent request) {
        this.request = request;
        return this;
    }

    public GameEvent getResponse() {
        return response;
    }

    public MadeMove setResponse(GameEvent response) {
        this.response = response;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public MadeMove setCreated(Date created) {
        this.created = created;
        return this;
    }

    @Override
    public int compareTo(MadeMove o) {
        return created.compareTo(o.created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MadeMove madeMove = (MadeMove) o;

        if (created != null ? !created.equals(madeMove.created) : madeMove.created != null) return false;
        if (request != null ? !request.equals(madeMove.request) : madeMove.request != null) return false;
        if (response != null ? !response.equals(madeMove.response) : madeMove.response != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = request != null ? request.hashCode() : 0;
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
