package com.clemble.casino.player.event;

public enum PlayerConnectionStatus {

    /**
     * connected - players connected
     */
    connected,
    /**
     * player send invitation to the connection, and waiting for responce
     */
    invited,
    /**
     * player was invited to connect
     */
    requested,
    /**
     * invitation was declined
     */
    invitation_declined,
    /**
     * request was declined
     */
    request_declined;

}
