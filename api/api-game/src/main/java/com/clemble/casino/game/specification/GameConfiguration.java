package com.clemble.casino.game.specification;

import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.payment.money.Money;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(name = "match", value = MatchGameConfiguration.class),
        @JsonSubTypes.Type(name = "pot", value = PotGameConfiguration.class),
        @JsonSubTypes.Type(name = "tournament", value = TournamentGameConfiguration.class),
})
public interface GameConfiguration extends GameConfigurationKeyAware {

    public Money getPrice();

    public PlayerNumberRule getNumberRule();

    public PrivacyRule getPrivacyRule();

}
