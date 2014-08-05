package com.clemble.casino.game.appointments;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Set;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.player.PlayerAware;

public class PlayerGameAppointments implements PlayerAware {

    /**
     * Generated 13/12/13
     */
    private static final long serialVersionUID = -9184102956712804125L;

    final private String player;
    final private Set<GameInitiation> availabilityAppointments;

    public PlayerGameAppointments(String player, Set<GameInitiation> availabilityAppointments) {
        this.player = checkNotNull(player);
        this.availabilityAppointments = checkNotNull(availabilityAppointments);
    }

    public String getPlayer() {
        return player;
    }

    public Set<GameInitiation> getAvailabilityAppointments() {
        return availabilityAppointments;
    }
}
