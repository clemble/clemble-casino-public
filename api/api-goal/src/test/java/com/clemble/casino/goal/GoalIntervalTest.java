package com.clemble.casino.goal;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalRoleConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.IntervalGoalConfigurationBuilder;
import com.clemble.casino.goal.lifecycle.configuration.rule.IntervalGoalRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.BasicReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.reminder.NoReminderRule;
import com.clemble.casino.goal.lifecycle.configuration.rule.share.ShareRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.LimitedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.breach.PenaltyBreachPunishment;
import com.clemble.casino.lifecycle.configuration.rule.timeout.MoveTimeoutCalculatorByEOD;
import com.clemble.casino.lifecycle.configuration.rule.timeout.MoveTimeoutRule;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TotalTimeoutCalculatorByEOD;
import com.clemble.casino.lifecycle.configuration.rule.timeout.TotalTimeoutRule;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.social.SocialProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Ignore;
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
        new Bet(Money.create(Currency.point, 0), Money.create(Currency.point, 0)),
        new BasicReminderRule(TimeUnit.HOURS.toMillis(4)),
        new BasicReminderRule(TimeUnit.HOURS.toMillis(2)),
        new MoveTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new MoveTimeoutCalculatorByEOD(7)),
        new TotalTimeoutRule(LooseBreachPunishment.getInstance(), new TotalTimeoutCalculatorByEOD(7)),
        new GoalRoleConfiguration(
            3,
            LimitedBetRule.create(50, 100),
            50,
            NoReminderRule.INSTANCE,
            NoReminderRule.INSTANCE
        ),
        ShareRule.EMPTY
    );

    final private List<IntervalGoalRule> intervalRules = ImmutableList.<IntervalGoalRule>of(
        new IntervalGoalRule(new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new TotalTimeoutCalculatorByEOD(2)), 50, 5),
        new IntervalGoalRule(new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 20)), new TotalTimeoutCalculatorByEOD(1)), 50, 5),
        new IntervalGoalRule(new ShareRule(ImmutableSet.of(SocialProvider.twitter)), 50, 5),
        new IntervalGoalRule(new ShareRule(ImmutableSet.of(SocialProvider.facebook)), 50, 5)
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
    @Ignore
    public void testDefaultConfiguration() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(150);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new TotalTimeoutCalculatorByEOD(7)));
        Assert.assertEquals(configuration.getShareRule(), ShareRule.EMPTY);
    }

    @Test
    @Ignore
    public void testPrivacyAsFriends() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(425);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new TotalTimeoutCalculatorByEOD(7)));
        Assert.assertEquals(configuration.getShareRule(), ShareRule.EMPTY);
    }

    @Test
    @Ignore
    public void testPrivacyAsWorld() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(475);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new TotalTimeoutCalculatorByEOD(7)));
        Assert.assertEquals(configuration.getShareRule(), ShareRule.EMPTY);
    }

    @Test
    public void testMoveTime2DaysRule() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(425);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 10)), new TotalTimeoutCalculatorByEOD(2)));
        Assert.assertEquals(configuration.getShareRule(), ShareRule.EMPTY);
    }

    @Test
    public void testMoveTimeDailyRule() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(475);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 20)), new TotalTimeoutCalculatorByEOD(1)));
        Assert.assertEquals(configuration.getShareRule(), ShareRule.EMPTY);
    }

    @Test
    public void testShareFacebook() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(525);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 20)), new TotalTimeoutCalculatorByEOD(1)));
        Assert.assertEquals(configuration.getShareRule(), new ShareRule(ImmutableSet.of(SocialProvider.twitter)));
    }

    @Test
    public void testShareTwitter() {
        GoalConfiguration configuration = goalBuilder.toConfiguration(575);
        Assert.assertEquals(configuration.getMoveTimeoutRule(), new TotalTimeoutRule(new PenaltyBreachPunishment(Money.create(Currency.point, 20)), new TotalTimeoutCalculatorByEOD(1)));
        Assert.assertEquals(configuration.getShareRule(), new ShareRule(ImmutableSet.of(SocialProvider.twitter, SocialProvider.facebook)));
    }

}
