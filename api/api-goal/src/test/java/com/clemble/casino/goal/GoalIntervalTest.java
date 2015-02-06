package com.clemble.casino.goal;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalRoleConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.IntervalGoalConfigurationBuilder;
import com.clemble.casino.goal.lifecycle.configuration.rule.IntervalGoalRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.BasicReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.NoReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.share.ShareRule;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.PenaltyBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.EODTimeoutCalculator;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TimeoutRule;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mavarazy on 2/6/15.
 */
public class GoalIntervalTest {

    final private GoalConfiguration base = new GoalConfiguration(
        "week",
        "Week",
        new Bet(Money.create(Currency.FakeMoney, 0), Money.create(Currency.FakeMoney, 0)),
        new BasicReminderRule(TimeUnit.HOURS.toMillis(4)),
        new BasicReminderRule(TimeUnit.HOURS.toMillis(2)),
        new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(7)),
        new TimeoutRule(LooseBreachPunishment.getInstance(), new EODTimeoutCalculator(7)),
        PrivacyRule.me,
        new GoalRoleConfiguration(
            new Bet(Money.create(Currency.FakeMoney, 50), Money.create(Currency.FakeMoney, 70)),
            3,
            NoReminderRule.INSTANCE,
            NoReminderRule.INSTANCE
        ),
        ShareRule.none
    );

    final private List<IntervalGoalRule> intervalRules = ImmutableList.<IntervalGoalRule>of(
        new IntervalGoalRule(PrivacyRule.friends, 50, 5),
        new IntervalGoalRule(PrivacyRule.world, 50, 5),
        new IntervalGoalRule(new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(2)), 50, 5),
        new IntervalGoalRule(new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 20)), new EODTimeoutCalculator(1)), 50, 5),
        new IntervalGoalRule(ShareRule.twitter, 50, 5),
        new IntervalGoalRule(ShareRule.facebook, 50, 5)
    );

    final private IntervalGoalConfigurationBuilder goalBuilder = new IntervalGoalConfigurationBuilder(
        base,
        100,
        300,
        30,
        intervalRules
    );



    @Test(expected = IllegalArgumentException.class)
    public void testFriendPrivacy() {
        goalBuilder.toConfiguration(50);
    }

    @Test
    public void testDefaultConfiguration() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(150);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(7)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.me);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.none);
    }

    @Test
    public void testPrivacyAsFriends() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(425);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(7)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.friends);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.none);
    }

    @Test
    public void testPrivacyAsWorld() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(475);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(7)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.world);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.none);
    }

    @Test
    public void testMoveTime2DaysRule() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(525);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 10)), new EODTimeoutCalculator(2)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.world);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.none);
    }

    @Test
    public void testMoveTimeDailyRule() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(575);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 20)), new EODTimeoutCalculator(1)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.world);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.none);
    }

    @Test
    public void testShareFacebook() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(625);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 20)), new EODTimeoutCalculator(1)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.world);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.twitter);
    }

    @Test
    public void testShareTwitter() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(675);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.FakeMoney, 20)), new EODTimeoutCalculator(1)));
        Assert.assertEquals(configuration.getPrivacyRule(), PrivacyRule.world);
        Assert.assertEquals(configuration.getShareRule(), ShareRule.facebook);
    }

}
