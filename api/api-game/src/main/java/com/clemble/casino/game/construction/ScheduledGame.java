package com.clemble.casino.game.construction;

import javax.persistence.*;
import javax.persistence.Id;

import com.clemble.casino.game.GameSessionAware;

@Entity
@Table(name = "GAME_SCHEDULE")
public class ScheduledGame implements GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = 1773102437262489956L;

    @Id
    @org.springframework.data.annotation.Id
    private String sessionKey;

    @Column(name = "START_TIME")
    private long startDate;

    public ScheduledGame() {
    }

    public ScheduledGame(String sessionKey, long startDate) {
        this.sessionKey = sessionKey;
        this.startDate = startDate;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startTime) {
        this.startDate = startTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sessionKey.hashCode();
        result = prime * result + (int) (startDate ^ (startDate >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScheduledGame other = (ScheduledGame) obj;
        if (!sessionKey.equals(other.sessionKey))
            return false;
        if (startDate != other.startDate)
            return false;
        return true;
    }

}
