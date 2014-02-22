package com.clemble.casino.configuration;

import com.clemble.casino.DNSBasedServerRegistry;
import com.clemble.casino.ServerRegistry;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerRegistryConfiguration {

    final private ServerRegistry playerNotificationBaseUrl;
    final private ServerRegistry playerRegistry;
    final private ServerRegistry paymentBaseUrl;
    final private ServerRegistry gameRegistry;

    public ServerRegistryConfiguration(String baseUrl) {
        this("notif.%s.player." + baseUrl, "%s.player." + baseUrl, "payment." + baseUrl, baseUrl);
    }

    public ServerRegistryConfiguration(String playerNotificationBaseUrl, String playerBaseUrl, String paymentBaseUrl, String gameBaseUrl) {
        this.playerNotificationBaseUrl = new DNSBasedServerRegistry(3, playerNotificationBaseUrl, playerNotificationBaseUrl, playerNotificationBaseUrl);
        this.paymentBaseUrl = new DNSBasedServerRegistry(3, paymentBaseUrl, paymentBaseUrl, paymentBaseUrl);
        this.playerRegistry = new DNSBasedServerRegistry(3, playerBaseUrl, playerBaseUrl, playerBaseUrl);
        this.gameRegistry = new DNSBasedServerRegistry(3, gameBaseUrl, gameBaseUrl, gameBaseUrl);
    }

    @JsonCreator
    public ServerRegistryConfiguration(
            @JsonProperty("playerNotificationRegistry") ServerRegistry playerNotificationRegistry,
            @JsonProperty("playerRegistry") ServerRegistry playerRegistry,
            @JsonProperty("paymentRegistry") ServerRegistry paymentRegistry,
            @JsonProperty("gameRegistry") ServerRegistry gameRegistry) {
        this.playerNotificationBaseUrl = playerNotificationRegistry;
        this.playerRegistry = playerRegistry;
        this.paymentBaseUrl = paymentRegistry;
        this.gameRegistry = gameRegistry;
    }

    public ServerRegistry getPlayerNotificationRegistry() {
        return playerNotificationBaseUrl;
    }

    public ServerRegistry getPlayerRegistry() {
        return playerRegistry;
    }

    public ServerRegistry getPaymentRegistry() {
        return paymentBaseUrl;
    }

    public ServerRegistry getGameRegistry() {
        return gameRegistry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameRegistry == null) ? 0 : gameRegistry.hashCode());
        result = prime * result + ((paymentBaseUrl == null) ? 0 : paymentBaseUrl.hashCode());
        result = prime * result + ((playerNotificationBaseUrl == null) ? 0 : playerNotificationBaseUrl.hashCode());
        result = prime * result + ((playerRegistry == null) ? 0 : playerRegistry.hashCode());
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
        ServerRegistryConfiguration other = (ServerRegistryConfiguration) obj;
        if (gameRegistry == null) {
            if (other.gameRegistry != null)
                return false;
        } else if (!gameRegistry.equals(other.gameRegistry))
            return false;
        if (paymentBaseUrl == null) {
            if (other.paymentBaseUrl != null)
                return false;
        } else if (!paymentBaseUrl.equals(other.paymentBaseUrl))
            return false;
        if (playerNotificationBaseUrl == null) {
            if (other.playerNotificationBaseUrl != null)
                return false;
        } else if (!playerNotificationBaseUrl.equals(other.playerNotificationBaseUrl))
            return false;
        if (playerRegistry == null) {
            if (other.playerRegistry != null)
                return false;
        } else if (!playerRegistry.equals(other.playerRegistry))
            return false;
        return true;
    }

}
