package com.clemble.casino.json;

import com.clemble.casino.player.event.PlayerDiscoveredConnectionEvent;
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
        module.registerSubtypes(new NamedType(PlayerDiscoveredConnectionEvent.class, PlayerDiscoveredConnectionEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
