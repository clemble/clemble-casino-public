package com.clemble.casino.player;

public class PlayerConnection implements PlayerAware {

    /**
     * Generated 18/12/13
     */
    private static final long serialVersionUID = -1819811612556136513L;

    // TODO add first name, last name and presence to be able to query it
    final private String player;
    final private PlayerConnectionStatus connectionStatus;

    public PlayerConnection(String player, PlayerConnectionStatus connectionStatus) {
        this.player = player;
        this.connectionStatus = connectionStatus;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public PlayerConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connectionStatus == null) ? 0 : connectionStatus.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerConnection other = (PlayerConnection) obj;
        if (connectionStatus != other.connectionStatus)
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

}
