package com.clemble.casino.game.specification;

import java.util.List;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.rule.bet.BetRule;
import com.clemble.casino.game.rule.bet.FixedBetRule;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.game.rule.giveup.GiveUpRule;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.game.rule.time.MoveTimeRule;
import com.clemble.casino.game.rule.time.TotalTimeRule;
import com.clemble.casino.game.rule.visibility.VisibilityRule;
import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.money.Money;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("match")
public class MatchGameConfiguration implements GameConfiguration {

    /**
     * Generated
     */
    private static final long serialVersionUID = 6573909004152898162L;

    final public static MatchGameConfiguration DEFAULT = new MatchGameConfiguration(
            new GameConfigurationKey(Game.pic, "DEFAULT"),
            Money.create(Currency.FakeMoney, 50),
            FixedBetRule.DEFAULT,
            GiveUpRule.lost,
            MoveTimeRule.DEFAULT,
            TotalTimeRule.DEFAULT,
            PrivacyRule.everybody,
            PlayerNumberRule.two,
            VisibilityRule.hidden,
            DrawRule.owned,
            WonRule.spent,
            CollectionUtils.immutableList("X", "O"));

    final private GameConfigurationKey configurationKey;
    final private Money price;
    final private BetRule betRule;
    final private GiveUpRule giveUpRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;
    final private PrivacyRule privacyRule;
    final private PlayerNumberRule numberRule;
    final private VisibilityRule visibilityRule;
    final private DrawRule drawRule;
    final private WonRule wonRule;
    final private List<String> roles;

    @JsonCreator
    public MatchGameConfiguration(
        @JsonProperty("configurationKey") GameConfigurationKey configurationKey,
        @JsonProperty("price") Money price,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("giveUpRule") GiveUpRule giveUpRule,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("numberRule") PlayerNumberRule numberRule,
        @JsonProperty("visibilityRule") VisibilityRule visibilityRule,
        @JsonProperty("drawRule") DrawRule drawRule,
        @JsonProperty("wonRule") WonRule wonRule,
        @JsonProperty("roles") List<String> roles
    ) {
        this.configurationKey = configurationKey;
        this.price = price;
        this.betRule = betRule;
        this.giveUpRule = giveUpRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
        this.numberRule = numberRule;
        this.visibilityRule = visibilityRule;
        this.drawRule = drawRule;
        this.wonRule = wonRule;
        this.roles = roles;
    }

    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    public PlayerNumberRule getNumberRule() {
        return numberRule;
    }

    public Money getPrice() {
        return price;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    public GiveUpRule getGiveUpRule() {
        return giveUpRule;
    }

    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    public VisibilityRule getVisibilityRule() {
        return visibilityRule;
    }

    public DrawRule getDrawRule() {
        return drawRule;
    }

    public WonRule getWonRule() {
        return wonRule;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((betRule == null) ? 0 : betRule.hashCode());
        result = prime * result + ((giveUpRule == null) ? 0 : giveUpRule.hashCode());
        result = prime * result + ((moveTimeRule == null) ? 0 : moveTimeRule.hashCode());
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
        result = prime * result + ((numberRule == null) ? 0 : numberRule.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((privacyRule == null) ? 0 : privacyRule.hashCode());
        result = prime * result + ((totalTimeRule == null) ? 0 : totalTimeRule.hashCode());
        result = prime * result + ((visibilityRule == null) ? 0 : visibilityRule.hashCode());
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
        MatchGameConfiguration other = (MatchGameConfiguration) obj;
        if (betRule == null) {
            if (other.betRule != null)
                return false;
        } else if (!betRule.equals(other.betRule))
            return false;
        if (giveUpRule != other.giveUpRule)
            return false;
        if (moveTimeRule == null) {
            if (other.moveTimeRule != null)
                return false;
        } else if (!moveTimeRule.equals(other.moveTimeRule))
            return false;
        if (configurationKey == null) {
            if (other.configurationKey != null)
                return false;
        } else if (!configurationKey.equals(other.configurationKey))
            return false;
        if (numberRule != other.numberRule)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (privacyRule != other.privacyRule)
            return false;
        if (totalTimeRule == null) {
            if (other.totalTimeRule != null)
                return false;
        } else if (!totalTimeRule.equals(other.totalTimeRule))
            return false;
        if (visibilityRule != other.visibilityRule)
            return false;
        return true;
    }

    public String toString(){
        return "spec:" + price + ":" + configurationKey;
    }

}
