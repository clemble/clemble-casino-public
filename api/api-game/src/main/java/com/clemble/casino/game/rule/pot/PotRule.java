package com.clemble.casino.game.rule.pot;

import com.clemble.casino.game.rule.GameRule;

public enum PotRule implements GameRule {

    no(0), two(2), three(3), four(4), five(5), six(6);

    final private int min;

    PotRule(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

}
