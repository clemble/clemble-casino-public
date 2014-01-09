package com.clemble.casino.game.construct;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.game.GamePlayerRole;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.specification.GameSpecification;
import com.clemble.casino.game.specification.GameSpecificationAware;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInitiation implements GameSpecificationAware, GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = -8584404446775359390L;

    final private GameSessionKey session;
    final private GameSpecification specification;
    final private LinkedHashSet<GamePlayerRole> participants = new LinkedHashSet<GamePlayerRole>();
    // TODO find a better implementation for this
    final private Set<String> confirmations = Collections.synchronizedSet(new LinkedHashSet<String>());

    public GameInitiation(GameConstruction construction) {
        this(construction.getSession(), construction.fetchAcceptedParticipants(), construction.getRequest().getSpecification());
    }

    public GameInitiation(GameSessionKey session, List<String> participants, GameSpecification specification) {
        this.specification = checkNotNull(specification);
        this.session = checkNotNull(session);

        List<String> roles = checkNotNull(specification.getRoles());
        for (int i = 0; i < participants.size(); i++)
            this.participants.add(new GamePlayerRole(participants.get(i), roles.get(i)));
    }

    public GameInitiation(GameSessionKey session, GameSpecification specification, Collection<GamePlayerRole> playerRoles) {
        this.specification = checkNotNull(specification);
        this.session = checkNotNull(session);
        this.participants.addAll(playerRoles);
    }

    @JsonCreator
    public GameInitiation(@JsonProperty("session") GameSessionKey session,
            @JsonProperty("specification") GameSpecification specification,
            @JsonProperty("participants") Collection<GamePlayerRole> playerRoles,
            @JsonProperty("confirmations") Collection<String> confirmations) {
        this.specification = checkNotNull(specification);
        this.session = checkNotNull(session);
        this.participants.addAll(playerRoles);
        this.confirmations.addAll(confirmations);
    }

    @Override
    public GameSpecification getSpecification() {
        return specification;
    }

    public LinkedHashSet<GamePlayerRole> getParticipants() {
        return participants;
    }

    @Override
    public GameSessionKey getSession() {
        return session;
    }

    public Set<String> getConfirmations() {
        return confirmations;
    }
    
    public void addConfirmations(Collection<String> players) {
        for(String player: players)
            addConfirmation(player);
    }

    public void addConfirmation(String player) {
        // Step 1. Sanity check
        if (!PlayerAwareUtils.contains(participants, player))
            throw ClembleCasinoException.fromError(ClembleCasinoError.GameInitiationInvalidPlayer);
        // Step 2. Adding to list of confirmed users
        this.confirmations.add(player);
    }

    public boolean confirmed() {
        return confirmations.size() == participants.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((confirmations == null) ? 0 : confirmations.hashCode());
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
        result = prime * result + ((specification == null) ? 0 : specification.hashCode());
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
        GameInitiation other = (GameInitiation) obj;
        if (confirmations == null) {
            if (other.confirmations != null)
                return false;
        } else if (!confirmations.equals(other.confirmations))
            return false;
        if (participants == null) {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        if (specification == null) {
            if (other.specification != null)
                return false;
        } else if (!specification.equals(other.specification))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "initiation:" + session + ":" + participants + ":" + confirmations;
    }

}
