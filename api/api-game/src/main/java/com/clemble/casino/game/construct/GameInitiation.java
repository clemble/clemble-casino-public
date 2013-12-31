package com.clemble.casino.game.construct;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import com.clemble.casino.game.GamePlayerRole;
import com.clemble.casino.game.GameRoleAware;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.specification.GameSpecification;
import com.clemble.casino.game.specification.GameSpecificationAware;

public class GameInitiation implements GameSpecificationAware, GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = -8584404446775359390L;

    final private GameSessionKey session;
    final private GameSpecification specification;
    final private LinkedHashSet<GamePlayerRole> participants;

    public GameInitiation(GameConstruction construction) {
        this(construction.getSession(), construction.fetchAcceptedParticipants(), construction.getRequest().getSpecification());
    }

    public GameInitiation(GameSessionKey session, List<String> participants, GameSpecification specification) {
        this.specification = checkNotNull(specification);
        this.session = checkNotNull(session);

        List<String> roles = checkNotNull(specification.getRoles());

        this.participants = new LinkedHashSet<GamePlayerRole>();
        for (int i = 0; i < participants.size(); i++)
            this.participants.add(new GamePlayerRole(participants.get(i), roles.get(i)));
    }

    public GameInitiation(GameSessionKey session, GameSpecification specification, Collection<GamePlayerRole> playerRoles) {
        this.specification = checkNotNull(specification);
        this.session = checkNotNull(session);
        this.participants = new LinkedHashSet<GamePlayerRole>(playerRoles);
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
}
