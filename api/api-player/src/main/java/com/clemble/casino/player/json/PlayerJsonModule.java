package com.clemble.casino.player.json;

import com.clemble.casino.player.event.PlayerDiscoveredConnectionEvent;
import com.clemble.casino.player.event.PlayerInvitationAcceptedAction;
import com.clemble.casino.player.event.PlayerInvitationDeclinedAction;
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

        return module;
    }

}
