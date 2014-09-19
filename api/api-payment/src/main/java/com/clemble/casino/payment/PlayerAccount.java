package com.clemble.casino.payment;

import java.util.Map;

import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerAccount implements PlayerAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = 6508845694631953306L;

    final private String player;
    final private Map<Currency, Money> money;

    @JsonCreator
    public PlayerAccount(@JsonProperty(PLAYER) String player, @JsonProperty("money") Map<Currency, Money> amounts) {
        this.player = player;
        this.money = CollectionUtils.immutableMap(amounts);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Map<Currency, Money> getMoney() {
        return money;
    }

    public Money getMoney(Currency currency) {
        if (currency == null)
            throw new IllegalArgumentException();
        return money.get(currency);
    }

    @Override
    public String toString() {
        return "account:" + player + ":" + money;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (player != null ? player.hashCode() : 0);
        result = prime * result + ((money == null) ? 0 : money.hashCode());
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
        PlayerAccount other = (PlayerAccount) obj;
        if (!player.equals(other.player))
            return false;
        if (money == null) {
            if (other.money != null)
                return false;
        } else if (!money.equals(other.money))
            return false;
        return true;
    }

}
