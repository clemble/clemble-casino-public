package com.clemble.casino.game.lifecycle.management;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.lifecycle.configuration.rule.time.MovePlayerClock;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundGamePlayerContext implements GamePlayerContext, GameRoleAware {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -6980469472050707009L;

    final private String role;
    final private String player;
    final private PlayerClock clock;
    final private MovePlayerClock moveClock;
    final private GamePlayerUnit units;
    final private GamePlayerAccount account;

    @JsonCreator
    public RoundGamePlayerContext(
            @JsonProperty(PLAYER) String player,
            @JsonProperty("account") GamePlayerAccount account,
            @JsonProperty("clock") PlayerClock clock,
            @JsonProperty("moveClock") MovePlayerClock moveClock,
            @JsonProperty("role") String role,
            @JsonProperty("units") GamePlayerUnit units) {
        this.role = role;
        this.clock = clock;
        this.moveClock = moveClock;
        this.units = units;
        this.player = player;
        this.account = account;
    }

    @Override
    public String getPlayer(){
        return player;
    }

    @Override
    public GamePlayerAccount getAccount() {
        return account;
    }

    @Override
    public PlayerClock getClock() {
        return clock;
    }

    @Override
    public MovePlayerClock getMoveClock() {
        return moveClock;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public GamePlayerUnit getUnits() {
        return units;
    }

    public static List<RoundGamePlayerContext> construct(GameInitiation initiation) {
        RoundGameConfiguration specification = (RoundGameConfiguration) initiation.getConfiguration();
        List<RoundGamePlayerContext> playerContexts = new ArrayList<RoundGamePlayerContext>();
        Iterator<String> players = initiation.getParticipants().iterator();
        for(String role: specification.getRoles()) {
            String player = players.next();
            GamePlayerAccount account = new GamePlayerAccount(specification.getPrice().getAmount(), 0, 0);
            PlayerClock clock = new PlayerClock(0, 0);
            GamePlayerUnit unit = new GamePlayerUnit(initiation.getConfiguration().getPlayerUnits());
            playerContexts.add(new RoundGamePlayerContext(player, account, clock, MovePlayerClock.DEFAULT, role, unit));
        }
        return playerContexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundGamePlayerContext)) return false;

        RoundGamePlayerContext that = (RoundGamePlayerContext) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (clock != null ? !clock.equals(that.clock) : that.clock != null) return false;
        if (moveClock != null ? !moveClock.equals(that.moveClock) : that.moveClock != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (clock != null ? clock.hashCode() : 0);
        result = 31 * result + (moveClock != null ? moveClock.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }
}
