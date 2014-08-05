package com.clemble.casino.json;

import com.clemble.casino.base.ExpectedEvent;
import com.clemble.casino.json.ClembleJsonModule;
import com.clemble.casino.player.PlayerDiscoveredConnectionEvent;
import com.clemble.casino.player.PlayerPresenceChangedEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by mavarazy on 8/5/14.
 */
public class PresenceJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Presence");
        module.registerSubtypes(new NamedType(PlayerPresenceChangedEvent.class, PlayerPresenceChangedEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
