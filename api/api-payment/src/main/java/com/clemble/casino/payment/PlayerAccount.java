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
import org.springframework.data.annotation.Version;


public class PlayerAccount implements PlayerAware, VersionAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = 6508845694631953306L;

    @Id
    final private String player;
    final private Map<Currency, Money> money;
    final private List<PendingOperation> pendingOperations;
    final private Integer version;

    @JsonCreator
    public PlayerAccount(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("money") Map<Currency, Money> money,
        @JsonProperty("pendingOperations") List<PendingOperation> pendingOperations,
        @JsonProperty("version") Integer version) {
        this.player = player;
        this.pendingOperations = pendingOperations;
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

    public List<PendingOperation> getPendingOperations(){
        return pendingOperations;
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
        if (!pendingOperations.equals(that.pendingOperations)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + pendingOperations.hashCode();
        result = 31 * result + version;
        return result;
    }

}
