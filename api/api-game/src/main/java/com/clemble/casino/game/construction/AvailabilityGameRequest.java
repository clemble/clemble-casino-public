package com.clemble.casino.game.construction;

import java.util.ArrayList;
import java.util.Collection;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.construction.ConstructionState;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.construction.event.InvitationAcceptedEvent;
import com.clemble.casino.game.construction.event.InvitationResponseEvent;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("availability")
public class AvailabilityGameRequest extends PlayerGameConstructionRequest implements GameParticipantsAware {

    /**
     * Generated 12/06/13
     */
    private static final long serialVersionUID = -3051736949418145655L;

    final private GameDeclineBehavior declineBehavior;
    final private Collection<String> participants;

    @JsonCreator
    public AvailabilityGameRequest(@JsonProperty(PLAYER) String player,
            @JsonProperty("configuration") GameConfiguration configuration,
            @JsonProperty("participants") Collection<String> participants,
            @JsonProperty("declineBehavior") GameDeclineBehavior declineBehavior) {
        super(player, configuration);
        this.declineBehavior = declineBehavior != null ? declineBehavior : GameDeclineBehavior.invalidate;
        if (!participants.contains(player)) {
            participants = new ArrayList<String>(participants);
            participants.add(player);
        }
        this.participants = CollectionUtils.immutableList(participants);
    }

    public GameDeclineBehavior getDeclineBehavior() {
        return declineBehavior;
    }

    @Override
    public Collection<String> getParticipants() {
        return participants;
    }

    @Override
    public GameConstruction toConstruction(String sessionKey) {
        // Step 1. Generating GameConstruction
        ActionLatch responses = new ActionLatch();
        responses.expectNext(participants, InvitationResponseEvent.class);
        responses.put(new InvitationAcceptedEvent(player, sessionKey));
        // Step 2. Creating new GameConstruction
        return new GameConstruction(sessionKey, player, ConstructionState.pending, responses, configuration, participants);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((declineBehavior == null) ? 0 : declineBehavior.hashCode());
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AvailabilityGameRequest other = (AvailabilityGameRequest) obj;
        if (declineBehavior != other.declineBehavior)
            return false;
        if (participants == null) {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        return true;
    }


}
