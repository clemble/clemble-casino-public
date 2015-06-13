package com.clemble.casino.player;

import org.springframework.social.connect.ConnectionKey;

/**
 * Created by mavarazy on 7/4/14.
 */
public interface ConnectionKeyAware {

    ConnectionKey getConnection();

}
