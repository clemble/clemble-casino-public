package com.clemble.casino.goal.lifecycle.configuration.rule.judge;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/10/14.
 */
public class JudgeRule implements GoalRule, PlayerAware {

    final private String player;
    final private JudgeType type;

    @JsonCreator
    public JudgeRule(@JsonProperty("player") String player, @JsonProperty("type") JudgeType type) {
        this.player = player;
        this.type = type;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public JudgeType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JudgeRule judgeRule = (JudgeRule) o;

        if (!player.equals(judgeRule.player)) return false;
        if (type != judgeRule.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
