package com.clemble.casino.bet;

import com.clemble.casino.Key;

/**
 * Created by mavarazy on 8/9/14.
 */
public class BetKey implements Key {

    final private String source;
    final private String id;

    public BetKey(String source, String id) {
        this.id = id;
        this.source = source;
    }

}
