package com.clemble.casino.base;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

public interface ExpectedEvent extends PlayerAwareEvent {

    public String getAction();

}
