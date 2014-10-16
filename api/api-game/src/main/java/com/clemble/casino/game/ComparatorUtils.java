package com.clemble.casino.game;

import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ComparatorUtils {

    public ComparatorUtils() {
        throw new IllegalAccessError();
    }

    final public static Comparator<Map.Entry<String, List<PlayerWonOutcome>>> WON_OUT_COMPARATOR = new Comparator<Map.Entry<String, List<PlayerWonOutcome>>>() {

        @Override
        public int compare(Map.Entry<String, List<PlayerWonOutcome>> o1, Map.Entry<String, List<PlayerWonOutcome>> o2) {
            return o1.getValue().size() - o2.getValue().size();
        };

    };

}
