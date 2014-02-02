package com.clemble.casino.game.specification;

import java.util.List;

import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.game.rule.pot.PotFillRule;
import com.clemble.casino.payment.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pot")
public class PotGameConfiguration implements GameConfiguration {

    /**
     * Generated 20/01/14
     */
    private static final long serialVersionUID = 8629255451613655461L;

    final private GameConfigurationKey configurationKey;
    final private Money price;
    final private PlayerNumberRule numberRule;
    final private PrivacyRule privacyRule;
    final private PotFillRule potFillRule;
    final private List<MatchGameConfiguration> matchConfigurations;

    @JsonCreator
    public PotGameConfiguration(
            @JsonProperty("configurationKey") GameConfigurationKey key,
            @JsonProperty("price") Money price,
            @JsonProperty("privacyRule") PrivacyRule privacyRule,
            @JsonProperty("numberRule") PlayerNumberRule numberRule,
            @JsonProperty("potFillRule") PotFillRule potFillRule,
            @JsonProperty("matchConfigurations") List<MatchGameConfiguration> configurations) {
        this.configurationKey = key;
        this.price = price;
        this.privacyRule = privacyRule;
        this.potFillRule = potFillRule;
        this.numberRule = numberRule;
        this.matchConfigurations = configurations;
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
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    public List<MatchGameConfiguration> getMatchConfigurations() {
        return matchConfigurations;
    }

    public PotFillRule getPotFillRule() {
        return potFillRule;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
        result = prime * result + ((matchConfigurations == null) ? 0 : matchConfigurations.hashCode());
        result = prime * result + ((numberRule == null) ? 0 : numberRule.hashCode());
        result = prime * result + ((potFillRule == null) ? 0 : potFillRule.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((privacyRule == null) ? 0 : privacyRule.hashCode());
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
        PotGameConfiguration other = (PotGameConfiguration) obj;
        if (configurationKey == null) {
            if (other.configurationKey != null)
                return false;
        } else if (!configurationKey.equals(other.configurationKey))
            return false;
        if (matchConfigurations == null) {
            if (other.matchConfigurations != null)
                return false;
        } else if (!matchConfigurations.equals(other.matchConfigurations))
            return false;
        if (numberRule != other.numberRule)
            return false;
        if (potFillRule != other.potFillRule)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (privacyRule != other.privacyRule)
            return false;
        return true;
    }

}
