package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.goal.lifecycle.management.GoalRole;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/**
 * Created by mavarazy on 12/13/14.
 */
@JsonTypeName("short")
public class GoalConfiguration implements
    Configuration,
    GoalConfigurationKeyAware {

    final private String configurationKey;
    final private PrivacyRule privacyRule;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;
    final private List<GoalRoleConfiguration> roleConfigurations;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("configurationKey") String configurationKey,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("roleConfigurations") List<GoalRoleConfiguration> roleConfigurations
    ) {
        this.configurationKey = configurationKey;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
        this.roleConfigurations = roleConfigurations;
    }

    @Override
    public String getConfigurationKey() {
        return configurationKey;
    }

    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    public List<GoalRoleConfiguration> getRoleConfigurations() {
        return roleConfigurations;
    }

    public GoalRoleConfiguration getRoleConfiguration(GoalRole goalRole) {
        for(GoalRoleConfiguration roleConfiguration: roleConfigurations)
            if (roleConfiguration.getRole() == goalRole)
                return roleConfiguration;
        return null;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;
        if (!roleConfigurations.equals(that.roleConfigurations)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = privacyRule.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        result = 31 * result + roleConfigurations.hashCode();
        return result;
    }

}
