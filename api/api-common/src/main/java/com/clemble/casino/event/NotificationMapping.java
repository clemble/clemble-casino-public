package com.clemble.casino.event;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerAware;

final public class NotificationMapping {

    final public static String PLAYER_CHANNEL_POSTFIX = "";

    final public static String PRESENCE_CHANNEL_POSTFIX = ".presence";

    final public static String TABLE_CHANNEL_POSTFIX = ".table";

    public static String toPlayerChannel(PlayerAware playerAware) {
        return playerAware.getPlayer();
    }

    public static String toPresenceChannel(String player){
        return player + PRESENCE_CHANNEL_POSTFIX;
    }
    
    public static String toPresenceChannel(PlayerAware playerAware) {
        return toPresenceChannel(playerAware.getPlayer());
    }

    public static String toTable(GameSessionKey sessionKey) {
        // TODO have a statement for table
        return sessionKey.getSession() + NotificationMapping.TABLE_CHANNEL_POSTFIX;
    }

}
