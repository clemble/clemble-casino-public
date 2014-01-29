package com.clemble.casino.game.specification;

import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.payment.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournament")
public class TournamentGameConfiguration implements GameConfiguration, GameConfigurationAware {

    /**
     * Generated 29/01/14
     */
    private static final long serialVersionUID = -1614743782407940253L;

    final private GameConfigurationKey configurationKey;
    final private Money price;
    final private PrivacyRule privacyRule;
    final private PlayerNumberRule numberRule;
    final private GameConfiguration configuration;

    @JsonCreator
    public TournamentGameConfiguration(
            @JsonProperty("configurationKey") GameConfigurationKey configurationKey,
            @JsonProperty("price") Money price,
            @JsonProperty("privacyRule") PrivacyRule privacyRule,
            @JsonProperty("numberRule") PlayerNumberRule numberRule,
            @JsonProperty("configuration") GameConfiguration configuration) {
        this.configurationKey = configurationKey;
        this.price = price;
        this.privacyRule = privacyRule;
        this.numberRule = numberRule;
        this.configuration = configuration;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
        result = prime * result + ((numberRule == null) ? 0 : numberRule.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        if (numberRule != other.numberRule)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

}
