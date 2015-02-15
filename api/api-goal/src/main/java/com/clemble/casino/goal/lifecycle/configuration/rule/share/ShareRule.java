package com.clemble.casino.goal.lifecycle.configuration.rule.share;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.clemble.casino.social.SocialProvider;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by mavarazy on 1/10/15.
 */
@JsonTypeName("rule:share")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class ShareRule implements GoalRule {

    final public static ShareRule EMPTY = new ShareRule(Collections.<SocialProvider>emptyList());

    final private Set<SocialProvider> providers;

    @JsonCreator
    public ShareRule(@JsonProperty("provider") Collection<SocialProvider> providers) {
        this.providers = CollectionUtils.immutableSet(providers);
    }

    public Set<SocialProvider> getProviders() {
        return providers;
    }

    public boolean isSet(SocialProvider provider) {
        return providers.contains(provider);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShareRule)) return false;

        ShareRule shareRule = (ShareRule) o;

        if (providers != null ? !providers.equals(shareRule.providers) : shareRule.providers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return providers != null ? providers.hashCode() : 0;
    }

}
