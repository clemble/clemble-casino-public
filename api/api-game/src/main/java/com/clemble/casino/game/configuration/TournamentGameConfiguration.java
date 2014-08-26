package com.clemble.casino.game.configuration;

import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.game.rule.time.MoveTimeRule;
import com.clemble.casino.game.rule.time.TotalTimeRule;
import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

// TODO Multilevel Configurations
@JsonTypeName("tournament")
public class TournamentGameConfiguration implements GameConfiguration, GameConfigurationAware {

    /**
     * Generated 29/01/14
     */
    private static final long serialVersionUID = -1614743782407940253L;

    final private GameConfigurationKey configurationKey;
    final private Money price;

    final private WonRule wonRule;
    final private DrawRule drawRule;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;
    final private PlayerNumberRule numberRule;

    final private GameConfiguration configuration;
    final private List<GameUnit> playerUnits;

    @JsonCreator
    public TournamentGameConfiguration(
            @JsonProperty("configurationKey") GameConfigurationKey configurationKey,
            @JsonProperty("price") Money price,
            @JsonProperty("privacyRule") PrivacyRule privacyRule,
            @JsonProperty("numberRule") PlayerNumberRule numberRule,
            @JsonProperty("configuration") GameConfiguration configuration,
            @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
            @JsonProperty("wonRule") WonRule wonRule,
            @JsonProperty("drawRule") DrawRule drawRule,
            @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
            @JsonProperty(value = "playerUnits", required = false) List<GameUnit> playerUnits) {
        this.price = price;
        this.wonRule = wonRule;
        this.drawRule = drawRule;
        this.numberRule = numberRule;
        this.privacyRule = privacyRule;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.configuration = configuration;
        this.configurationKey = configurationKey;
        this.playerUnits = playerUnits;
    }

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    @Override
    public PlayerNumberRule getNumberRule() {
        return numberRule;
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    @Override
    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    @Override
    public WonRule getWonRule() {
        return wonRule;
    }

    @Override
    public DrawRule getDrawRule() {
        return drawRule;
    }

    @Override
    public List<GameUnit> getPlayerUnits() {
        return playerUnits;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
        result = prime * result + ((moveTimeRule == null) ? 0 : moveTimeRule.hashCode());
        result = prime * result + ((numberRule == null) ? 0 : numberRule.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((privacyRule == null) ? 0 : privacyRule.hashCode());
        result = prime * result + ((totalTimeRule == null) ? 0 : totalTimeRule.hashCode());
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
        TournamentGameConfiguration other = (TournamentGameConfiguration) obj;
        if (configuration == null) {
            if (other.configuration != null)
                return false;
        } else if (!configuration.equals(other.configuration))
            return false;
        if (configurationKey == null) {
            if (other.configurationKey != null)
                return false;
        } else if (!configurationKey.equals(other.configurationKey))
            return false;
        if (moveTimeRule == null) {
            if (other.moveTimeRule != null)
                return false;
        } else if (!moveTimeRule.equals(other.moveTimeRule))
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
        return true;
    }

}
