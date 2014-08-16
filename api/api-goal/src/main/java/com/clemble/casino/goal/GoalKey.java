package com.clemble.casino.goal;

import com.clemble.casino.Key;
import com.clemble.casino.payment.PaymentTransactionAwareConvertible;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/11/14.
 */
public class GoalKey implements PlayerAware, PaymentTransactionAwareConvertible, Key {

    final private String player;
    final private String id;

    @JsonCreator
    public GoalKey(@JsonProperty("player") String player, @JsonProperty("id") String id) {
        this.player = player;
        this.id = id;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getId() {
        return id;
    }

    @Override
    public PaymentTransactionKey toPaymentTransactionKey() {
        return new PaymentTransactionKey(player, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalKey goalKey = (GoalKey) o;

        if (id != null ? !id.equals(goalKey.id) : goalKey.id != null) return false;
        if (player != null ? !player.equals(goalKey.player) : goalKey.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "goal:key:" + player + ":" + id;
    }
}
