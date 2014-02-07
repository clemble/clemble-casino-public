package com.clemble.casino.game;

import java.util.Comparator;

public class ComparatorUtils {

    public ComparatorUtils() {
        throw new IllegalAccessError();
    }

    final public static Comparator<PotGamePlayerContext> WON_SIZE_COMPARATOR = new Comparator<PotGamePlayerContext>() {

        @Override
        public int compare(PotGamePlayerContext o1, PotGamePlayerContext o2) {
            return o1.getWonOutcomes().size() - o2.getWonOutcomes().size();
        }

    };

}
