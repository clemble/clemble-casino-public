package com.clemble.casino.game.configuration;

import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.money.Money;

import java.util.List;

public interface GameConfiguration extends GameConfigurationKeyAware, GameAware, Configuration {

    Money getPrice();

    MoveTimeRule getMoveTimeRule();

    TotalTimeRule getTotalTimeRule();

    PlayerNumberRule getNumberRule();

    WonRule getWonRule();

    DrawRule getDrawRule();

    List<GameUnit> getPlayerUnits();

}
