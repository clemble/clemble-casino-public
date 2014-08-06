package com.clemble.casino.social;

import org.springframework.social.connect.ConnectionKey;

/**
 * Created by mavarazy on 7/29/14.
 */
final public class ClembleSocialUtils {

    private ClembleSocialUtils(){
        throw new IllegalArgumentException();
    }

    public static String toString(ConnectionKey connectionKey){
        return connectionKey != null ? (connectionKey.getProviderId() + ":" + connectionKey.getProviderUserId()) : ":";
    }

    public static ConnectionKey fromString(String connectionKey) {
        if(connectionKey == null)
            return null;
        String[] parts = connectionKey.split(":");
        if(parts.length != 2)
            return null;
        return new ConnectionKey(parts[0], parts[1]);
    }
}


