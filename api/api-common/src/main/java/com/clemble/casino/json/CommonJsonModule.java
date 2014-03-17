package com.clemble.casino.json;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.clemble.casino.base.SimpleExpectedEvent;
import com.clemble.casino.DNSBasedServerRegistry;
import com.clemble.casino.player.PlayerDiscoveredConnectionEvent;
import com.clemble.casino.player.PlayerPresenceChangedEvent;

class CommonJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Common");
        module.registerSubtypes(new NamedType(SimpleExpectedEvent.class, SimpleExpectedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(DNSBasedServerRegistry.class, DNSBasedServerRegistry.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerDiscoveredConnectionEvent.class, PlayerDiscoveredConnectionEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerPresenceChangedEvent.class, PlayerPresenceChangedEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}