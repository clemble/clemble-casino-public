package com.clemble.casino.game.configuration;

import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.rule.Configuration;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameConfiguration extends GameConfigurationKeyAware, GameAware, Configuration {

    public PlayerNumberRule getNumberRule();

    public WonRule getWonRule();

    public DrawRule getDrawRule();

    public List<GameUnit> getPlayerUnits();

}
