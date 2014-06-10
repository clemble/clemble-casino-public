package com.clemble.casino.payment;

import java.util.Collection;
import java.util.Set;

import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.utils.CollectionUtils;
import com.clemble.casino.web.payment.PaymentWebMapping;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.module.jsonSchema.annotation.JsonHyperSchema;
//import com.fasterxml.jackson.module.jsonSchema.annotation.Link;
//
//@JsonHyperSchema(links = {
//        @Link(rel = "self", href = PaymentWebMapping.ACCOUNTS_PLAYER),
//        @Link(rel = "transactions", href= PaymentWebMapping.PAYMENT_ACCOUNTS_PLAYER_TRANSACTIONS, targetSchema = PaymentTransaction.class)
//    }
//)
public class PlayerAccount implements PlayerAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = 6508845694631953306L;

    final private String player;
    final private Set<Money> cash;

    @JsonCreator
    public PlayerAccount(@JsonProperty("player") String player, @JsonProperty("money") Collection<Money> amounts) {
        this.player = player;
        this.cash = CollectionUtils.immutableSet(amounts);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Set<Money> getMoney() {
        return cash;
    }

    public Money getMoney(Currency currency) {
        if (currency == null)
            return Money.create(currency, 0);

        for (Money money : cash)
            if (money.getCurrency() == currency)
                return money;

        return Money.create(currency, 0);
    }

    @Override
    public String toString() {
        return "account:" + player + ":" + cash;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (player != null ? player.hashCode() : 0);
        result = prime * result + ((cash == null) ? 0 : cash.hashCode());
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
        if (cash == null) {
            if (other.cash != null)
                return false;
        } else if (!cash.equals(other.cash))
            return false;
        return true;
    }

}
