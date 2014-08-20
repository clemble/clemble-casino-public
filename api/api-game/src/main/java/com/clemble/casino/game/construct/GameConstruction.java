package com.clemble.casino.game.construct;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import org.hibernate.annotations.Type;

import com.clemble.casino.VersionAware;
import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.event.schedule.InvitationAcceptedEvent;

@Entity
@Table(name = "GAME_CONSTRUCTION")
public class GameConstruction implements GameSessionAware, VersionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = 2712386710995109913L;

    @Id
    private String session;

    @Type(type = "com.clemble.casino.game.construct.GameRequestHibernate")
    @Column(name = "REQUEST", length = 8192, nullable = false)
    private GameConstructionRequest request;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameConstructionState state;

    @Type(type = "com.clemble.casino.base.ActionLatchHibernate")
    @Column(name = "RESPONSES", length = 8192, nullable = false)
    private ActionLatch responses = new ActionLatch();

    @Version
    private int version;

    public GameConstruction() {
    }

    public GameConstruction(GameConstructionRequest request) {
        this.request = request;
        this.state = GameConstructionState.pending;
    }

    public GameConstruction(String sessionKey, AvailabilityGameRequest request) {
        this.request = request;
        this.session = sessionKey;
        this.state = GameConstructionState.pending;
        this.responses.expectNext(request.getParticipants(), InvitationResponseEvent.class);
        this.responses.put(new InvitationAcceptedEvent(request.getPlayer(), this.session));
    }

    @Override
    public String getSessionKey() {
        return session;
    }

    public GameConstruction setSessionKey(String session) {
        this.session = session;
        return this;
    }

    public GameConstructionRequest getRequest() {
        return request;
    }

    public GameConstruction setRequest(GameConstructionRequest gameRequest) {
        this.request = gameRequest;
        return this;
    }

    public GameConstructionState getState() {
        return state;
    }

    public GameConstruction setState(GameConstructionState state) {
        this.state = state;
        return this;
    }

    public ActionLatch getResponses() {
        return responses;
    }

    public GameConstruction setResponses(ActionLatch responses) {
        this.responses = responses;
        return this;
    }

    public List<String> fetchAcceptedParticipants() {
        List<String> acceptedParticipants = new ArrayList<String>(responses.fetchParticipants().size());

        for (PlayerAwareEvent responseEntry : responses.getActions()) {
            if (responseEntry instanceof InvitationAcceptedEvent)
                acceptedParticipants.add(responseEntry.getPlayer());
        }

        return acceptedParticipants;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public GameInitiation toInitiation() {
        return new GameInitiation(this);
    }

}
