package com.clemble.casino.player.security;

import java.util.Date;

import com.clemble.casino.configuration.ResourceLocations;
import com.clemble.casino.player.PlayerAware;
import org.springframework.data.annotation.Id;

public class PlayerSession implements PlayerAware {

    /**
     * 
     */
    private static final long serialVersionUID = 5003851677194227089L;

    @Id
    // TODO consider having unique PLAYER:SESSION identifier
    private String sessionId;

    private String player;

    private Date startTime = new Date();

    private Date expirationTime = new Date();

    private ResourceLocations resourceLocations;

    public String getSessionId() {
        return sessionId;
    }

    public PlayerSession setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getPlayer() {
        return player;
    }

    public PlayerSession setPlayer(String playerId) {
        this.player = playerId;
        return this;
    }

    public ResourceLocations getResourceLocations() {
        return resourceLocations;
    }

    public void setResourceLocations(ResourceLocations resourceLocations) {
        this.resourceLocations = resourceLocations;
    }

    public Date getStartTime() {
        return startTime;
    }

    public PlayerSession setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean expired() {
        return System.currentTimeMillis() >= expirationTime.getTime();
    }

    public void markExpired() {
        this.expirationTime = new Date();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (player == null ? 0 : player.hashCode());
        result = prime * result + (int) (sessionId == null ? 0 : sessionId.hashCode());
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
        PlayerSession other = (PlayerSession) obj;
        if (!sessionId.equals(other.sessionId))
            return false;
        return player.equals(other.player);
    }

    @Override
    public String toString() {
        return "PlayerSession [sessionId=" + sessionId + ", playerId=" + player + ", startTime=" + startTime + ", expirationTime=" + expirationTime + "]";
    }

}
