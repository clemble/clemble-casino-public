package com.clemble.casino.game.configuration;

import java.util.Collections;
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
import com.clemble.casino.game.rule.time.TimeBreachPunishment;
import com.clemble.casino.game.rule.time.TotalTimeRule;
import com.clemble.casino.game.rule.visibility.VisibilityRule;
import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("round")
public class RoundGameConfiguration implements GameConfiguration {

    /**
     * Generated
     */
    private static final long serialVersionUID = 6573909004152898162L;

    final public static RoundGameConfiguration DEFAULT = new RoundGameConfiguration(
            new GameConfigurationKey(Game.pic, "DEFAULT"),
            Money.create(Currency.FakeMoney, 50),
            FixedBetRule.DEFAULT,
            GiveUpRule.lost,
            new MoveTimeRule(4000, TimeBreachPunishment.loose),
            new TotalTimeRule(4000, TimeBreachPunishment.loose),
            PrivacyRule.everybody,
            PlayerNumberRule.two,
            VisibilityRule.hidden,
            DrawRule.owned,
            WonRule.spent,
            CollectionUtils.immutableList("X", "O"),
            Collections.<GameUnit>emptyList());

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
    final private List<GameUnit> playerUnits;

    @JsonCreator
    public RoundGameConfiguration(
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
            @JsonProperty("roles") List<String> roles,
            @JsonProperty(value = "playerUnits", required = false) List<GameUnit> playerUnits
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
        this.playerUnits = playerUnits;
    }

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public PlayerNumberRule getNumberRule() {
        return numberRule;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    public GiveUpRule getGiveUpRule() {
        return giveUpRule;
    }

    @Override
    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    @Override
    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    public VisibilityRule getVisibilityRule() {
        return visibilityRule;
    }

    @Override
    public DrawRule getDrawRule() {
        return drawRule;
    }

    @Override
    public WonRule getWonRule() {
        return wonRule;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public List<GameUnit> getPlayerUnits() {
        return playerUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoundGameConfiguration that = (RoundGameConfiguration) o;

        if (betRule != null ? !betRule.equals(that.betRule) : that.betRule != null) return false;
        if (configurationKey != null ? !configurationKey.equals(that.configurationKey) : that.configurationKey != null)
            return false;
        if (drawRule != that.drawRule) return false;
        if (giveUpRule != that.giveUpRule) return false;
        if (moveTimeRule != null ? !moveTimeRule.equals(that.moveTimeRule) : that.moveTimeRule != null) return false;
        if (numberRule != that.numberRule) return false;
        if (playerUnits != null ? !playerUnits.equals(that.playerUnits) : that.playerUnits != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (privacyRule != that.privacyRule) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        if (totalTimeRule != null ? !totalTimeRule.equals(that.totalTimeRule) : that.totalTimeRule != null)
            return false;
        if (visibilityRule != that.visibilityRule) return false;
        if (wonRule != that.wonRule) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configurationKey != null ? configurationKey.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (betRule != null ? betRule.hashCode() : 0);
        result = 31 * result + (giveUpRule != null ? giveUpRule.hashCode() : 0);
        result = 31 * result + (moveTimeRule != null ? moveTimeRule.hashCode() : 0);
        result = 31 * result + (totalTimeRule != null ? totalTimeRule.hashCode() : 0);
        result = 31 * result + (privacyRule != null ? privacyRule.hashCode() : 0);
        result = 31 * result + (numberRule != null ? numberRule.hashCode() : 0);
        result = 31 * result + (visibilityRule != null ? visibilityRule.hashCode() : 0);
        result = 31 * result + (drawRule != null ? drawRule.hashCode() : 0);
        result = 31 * result + (wonRule != null ? wonRule.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (playerUnits != null ? playerUnits.hashCode() : 0);
        return result;
    }

    public String toString(){
        return "spec:" + price + ":" + configurationKey;
    }

}
