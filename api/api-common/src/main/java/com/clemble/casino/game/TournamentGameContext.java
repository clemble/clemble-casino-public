package com.clemble.casino.game;

import java.util.List;

import com.clemble.casino.game.construct.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournamentContext")
public class TournamentGameContext extends GameContext<TournamentGamePlayerContext> {

    /**
     * Generated 01/04/14
     */
    private static final long serialVersionUID = 8852504586116283067L;

    final private TournamentLeaf leaf;

    public TournamentGameContext(GameInitiation initiation, TournamentLeaf leaf, GameContext<?> parent) {
        super(initiation.getSession(), parent, TournamentGamePlayerContext.construct(initiation));
        this.leaf = leaf;
    }


    @JsonCreator
    public TournamentGameContext(
            @JsonProperty("session") GameSessionKey sessionKey,
            @JsonProperty("parent") GameContext<?> parent,
            @JsonProperty("playerContexts") List<TournamentGamePlayerContext> playerContexts,
            @JsonProperty("leaf") TournamentLeaf leaf) {
        super(sessionKey, parent, playerContexts);
        this.leaf = leaf;
    }

    public TournamentLeaf getLeaf(){
        return leaf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TournamentGameContext that = (TournamentGameContext) o;

        if (leaf != null ? !leaf.equals(that.leaf) : that.leaf != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        return result;
    }
}
