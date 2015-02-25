package com.clemble.casino.game.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.lifecycle.configuration.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.lifecycle.configuration.rule.outcome.DrawRule;
import com.clemble.casino.game.lifecycle.configuration.rule.outcome.WonRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "configuration")
public interface GameConfiguration extends GameConfigurationKeyAware, GameAware, Configuration {

    @Id
    String getConfigurationKey();

    Money getPrice();

    MoveTimeRule getMoveTimeRule();

    TotalTimeRule getTotalTimeRule();

    PlayerNumberRule getNumberRule();

    WonRule getWonRule();

    DrawRule getDrawRule();

    List<GameUnit> getPlayerUnits();

}
