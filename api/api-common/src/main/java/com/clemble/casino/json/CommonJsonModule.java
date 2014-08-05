package com.clemble.casino.json;

import com.clemble.casino.base.ExpectedEvent;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.clemble.casino.player.PlayerDiscoveredConnectionEvent;

class CommonJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");
        module.registerSubtypes(new NamedType(ExpectedEvent.class, ExpectedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerDiscoveredConnectionEvent.class, PlayerDiscoveredConnectionEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
