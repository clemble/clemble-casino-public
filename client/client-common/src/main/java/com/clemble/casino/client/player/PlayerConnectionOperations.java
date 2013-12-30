package com.clemble.casino.client.player;

import java.util.List;

public interface PlayerConnectionOperations {

    public List<String> getConnections();
    
    public List<String> getConnections(String player);

}
