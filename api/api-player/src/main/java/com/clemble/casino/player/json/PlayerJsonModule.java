package com.clemble.casino.player.json;

import com.clemble.casino.player.event.PlayerDiscoveredConnectionEvent;
import com.clemble.casino.player.event.PlayerInvitationAcceptedAction;
import com.clemble.casino.player.event.PlayerInvitationDeclinedAction;
import com.clemble.casino.player.notification.PlayerConnectedNotification;
import com.clemble.casino.player.notification.PlayerDiscoveredNotification;
import com.clemble.casino.player.notification.PlayerInvitedNotification;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.clemble.casino.json.ClembleJsonModule;

/**
 * Created by mavarazy on 8/6/14.
 */
public class PlayerJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");
        module.registerSubtypes(new NamedType(PlayerDiscoveredConnectionEvent.class, PlayerDiscoveredConnectionEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(PlayerInvitationAcceptedAction.class, PlayerInvitationAcceptedAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerInvitationDeclinedAction.class, PlayerInvitationDeclinedAction.JSON_TYPE));

        module.registerSubtypes(new NamedType(PlayerConnectedNotification.class, PlayerConnectedNotification.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerDiscoveredNotification.class, PlayerDiscoveredNotification.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerInvitedNotification.class, PlayerInvitedNotification.JSON_TYPE));

        return module;
    }

}
