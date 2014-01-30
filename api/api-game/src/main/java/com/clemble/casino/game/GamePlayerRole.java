package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 21/12/13.
 */
@Embeddable
public class GamePlayerRole implements PlayerAware, GameRoleAware {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = -2977977479889713389L;

    @Column(name = "PLAYER")
    final private String player;
    @Column(name = "ROLE")
    final private String role;

    @JsonCreator
    public GamePlayerRole(@JsonProperty("player") String player, @JsonProperty("role") String role) {
        this.player = player;
        this.role = role;
    }

    public String getPlayer() {
        return player;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GamePlayerRole that = (GamePlayerRole) o;

        if (player != null ? !player.equals(that.player) : that.player != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return player + ':' + role;
    }

    public static List<GamePlayerRole> rotate(Collection<GamePlayerRole> players) {
        // Step 1. Sanity check
        if(players == null || players.size() < 2)
            return CollectionUtils.immutableList();
        // Step 2. Rotating list
        GamePlayerRole[] playersArray = players.toArray(new GamePlayerRole[0]);
        List<GamePlayerRole> rotated = new ArrayList<GamePlayerRole>();
        for (int i = 0; i < playersArray.length; i++)
            rotated.add(new GamePlayerRole(playersArray[i].getPlayer(), playersArray[(i + 1) % playersArray.length].getRole()));
        return rotated;
    }
}
