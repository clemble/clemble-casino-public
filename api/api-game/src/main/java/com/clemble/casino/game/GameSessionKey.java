package com.clemble.casino.game;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.clemble.casino.Key;
import com.clemble.casino.bet.BetAware;
import com.clemble.casino.bet.BetKey;
import com.clemble.casino.payment.PaymentTransactionAwareConvertible;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class GameSessionKey implements GameAware, BetAware, PaymentTransactionAwareConvertible, Serializable, Key {

    /**
     * Generated 31/01/14
     */
    private static final long serialVersionUID = 7812547584769033422L;

    final public static GameSessionKey DEFAULT_SESSION = new GameSessionKey();

    @Column(name = "GAME")
    @Enumerated(EnumType.STRING)
    private Game game;

    @Column(name = "SESSION_ID")
    private String session;

    public GameSessionKey() {
    }

    @JsonCreator
    public GameSessionKey(@JsonProperty("game") Game game, @JsonProperty(GameSessionAware.SESSION_KEY) String session) {
        this.game = game;
        this.session = session;
    }

    @Override
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public GameSessionKey append(String postfix) {
        return new GameSessionKey(game, session + postfix);
    }

    public static GameSessionKey fromString(String sessionKey) {
        // Step 1. Sanity check
        if(sessionKey == null)
            return null;
        // Step 2. Parsing session key
        String[] session = sessionKey.split(":");
        if(session.length == 0 || session[0].length() == 0 && session[1].length() == 0)
            return GameSessionKey.DEFAULT_SESSION;
        else
            return new GameSessionKey(Game.valueOf(session[0]), session[1]);
    }

    @Override
    public PaymentTransactionKey toPaymentTransactionKey(){
        return new PaymentTransactionKey(game.name(), session);
    }

    @Override
    public BetKey toBetKey() {
        return new BetKey(game.name(), session);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((game == null) ? 0 : game.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
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
        GameSessionKey other = (GameSessionKey) obj;
        return game == other.game && (session == other.session || session.equals(other.session));
    }

    @Override
    public String toString() {
        return game + ":" + session;
    }

}
