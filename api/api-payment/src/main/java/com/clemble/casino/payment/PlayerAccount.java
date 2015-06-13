package com.clemble.casino.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clemble.casino.VersionAware;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;


public class PlayerAccount implements PlayerAware, VersionAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = 6508845694631953306L;

    @Id
    final private String player;
    final private Map<Currency, Money> money;
    final private Integer version;

    @JsonCreator
    public PlayerAccount(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("money") Map<Currency, Money> money,
        @JsonProperty("version") Integer version) {
        this.player = player;
        this.money = new HashMap<Currency, Money>(money);
        this.version = version;
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

    public PlayerAccount process(PaymentOperation operation) {
        Money originalAmount = getMoney(operation.getAmount().getCurrency());
        if (originalAmount == null) {
            PaymentOperation debitOperation = operation.toDebit();
            money.put(debitOperation.getAmount().getCurrency(), debitOperation.getAmount());
        } else {
            money.put(originalAmount.getCurrency(), originalAmount.process(operation.getOperation(), operation.getAmount()));
        }
        return this;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "account:" + player + ":" + money + ":" + version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerAccount)) return false;

        PlayerAccount that = (PlayerAccount) o;

        if (version != that.version) return false;
        if (!money.equals(that.money)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + version;
        return result;
    }

}
