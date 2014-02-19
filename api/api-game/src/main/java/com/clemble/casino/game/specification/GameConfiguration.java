package com.clemble.casino.game.specification;

import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.game.rule.time.MoveTimeRule;
import com.clemble.casino.game.rule.time.TotalTimeRule;
import com.clemble.casino.payment.money.Money;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameConfiguration extends GameConfigurationKeyAware {

    public Money getPrice();

    public PlayerNumberRule getNumberRule();

    public PrivacyRule getPrivacyRule();

    public TotalTimeRule getTotalTimeRule();

    public MoveTimeRule getMoveTimeRule();

    public WonRule getWonRule();

    public DrawRule getDrawRule();

}
