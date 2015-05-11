package com.clemble.casino.json;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRuleValue;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.bet.*;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.clemble.casino.player.event.PlayerInvitationAcceptedAction;
import com.clemble.casino.goal.lifecycle.management.GoalPlayerContext;
import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.GiveUpAction;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.clemble.casino.security.ClientDetails;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.common.signature.RSAKeySecret;

import com.clemble.casino.VersionAware;
//import com.clemble.casino.game.lifecycle.management.*;
//import com.clemble.casino.game.*;
//import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
//import com.clemble.casino.game.lifecycle.management.unit.Chip;
//import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
//import com.clemble.casino.game.lifecycle.construction.AutomaticGameRequest;
//import com.clemble.casino.game.lifecycle.construction.GameConstruction;
//import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
//import com.clemble.casino.game.lifecycle.configuration.rule.construct.PlayerNumberRule;
//import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
//import com.clemble.casino.game.lifecycle.configuration.TournamentGameConfiguration;
import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.player.PlayerGender;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.registration.PlayerCredential;
import com.clemble.casino.utils.ClembleConsumerDetailUtils;
import com.clemble.test.random.AbstractValueGenerator;
import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.random.ValueGenerator;
import com.google.common.collect.ImmutableList;

public class TestObjectGeneratorInitializer {

    public static void init() {
        ObjectGenerator.register(DateTimeZone.class, new AbstractValueGenerator<DateTimeZone>() {
            @Override
            public DateTimeZone generate() {
                return DateTimeZone.UTC;
            }
        });
        ObjectGenerator.register(DateTime.class, new AbstractValueGenerator<DateTime>() {
            @Override
            public DateTime generate() {
                return new DateTime(ObjectGenerator.generate(long.class));
            }
        });
        ObjectGenerator.register(Configuration.class, new AbstractValueGenerator<Configuration>() {
            @Override
            public Configuration generate() {
                return new BetConfiguration(UnlimitedBetRule.INSTANCE);
            }
        });
//        ObjectGenerator.register(TournamentGameState.class, new AbstractValueGenerator<TournamentGameState>() {
//            @Override
//            public TournamentGameState generate() {
//                TournamentGameContext context = new TournamentGameContext(
//                    "",
//                    null,
//                    ObjectGenerator.generateList(TournamentGamePlayerContext.class, 10),
//                    null);
//                return new TournamentGameState(
//                    ObjectGenerator.generate(TournamentGameConfiguration.class),
//                    context,
//                    null,
//                    0);
//            }
//        });
        ObjectGenerator.register(GoalRuleValue.class, new AbstractValueGenerator<GoalRuleValue>() {
            @Override
            public GoalRuleValue generate() {
                return new GoalRuleValue(ObjectGenerator.generate(TotalTimeRule.class), ObjectGenerator.generate(Integer.class));
            }
        });
        ObjectGenerator.register(FixedBidRule.class, new AbstractValueGenerator<FixedBidRule>() {
            @Override
            public FixedBidRule generate() {
                return FixedBidRule.create(ObjectGenerator.generate(Bet.class));
            }
        });
        ObjectGenerator.register(SortedSet.class, new AbstractValueGenerator<SortedSet>() {
            @Override
            public SortedSet generate() {
                return new TreeSet();
            }
        });
//        ObjectGenerator.register(GameUnit.class, new AbstractValueGenerator<GameUnit>() {
//            @Override
//            public GameUnit generate() {
//                return Chip.zero;
//            }
//        });
        ObjectGenerator.register(PlayerExpectedAction.class, new AbstractValueGenerator<PlayerExpectedAction>() {
            @Override
            public PlayerExpectedAction generate() {
            return PlayerExpectedAction.fromClass(PlayerInvitationAcceptedAction.class);
            }
        });
//        ObjectGenerator.register(RoundGameContext.class, new AbstractValueGenerator<RoundGameContext>() {
//            @Override
//            public RoundGameContext generate() {
//            GameInitiation initiation = new GameInitiation(GameSessionAware.DEFAULT_SESSION, InitiationState.pending, ImmutableList.of("A", "B"), RoundGameConfiguration.DEFAULT);
//            return RoundGameContext.fromInitiation(initiation, null);
//            }
//
//        });
        ObjectGenerator.register(FixedBetRule.class, new AbstractValueGenerator<FixedBetRule>() {
            @Override
            public FixedBetRule generate() {
            return FixedBetRule.create(10);
            }
        });
        ObjectGenerator.register(Event.class, new AbstractValueGenerator<Event>() {
            @Override
            public Event generate() {
                return new GiveUpAction();
            }
        });
        ObjectGenerator.register(BetAction.class, new AbstractValueGenerator<BetAction>() {
            @Override
            public BetAction generate() {
            return new BetAction(100);
            }
        });
        ObjectGenerator.register(PlayerAccount.class, new AbstractValueGenerator<PlayerAccount>() {
            @Override
            public PlayerAccount generate() {
            return new PlayerAccount(
                RandomStringUtils.random(5),
                ImmutableMap.of(Currency.point, Money.create(Currency.point, 500)),
                null);
            }
        });
        ObjectGenerator.register(PaymentTransaction.class, new AbstractValueGenerator<PaymentTransaction>() {
            @Override
            public PaymentTransaction generate() {
            return new PaymentTransaction().
                setTransactionKey(RandomStringUtils.random(5)).
                setTransactionDate(DateTime.now(DateTimeZone.UTC)).
                setProcessingDate(DateTime.now(DateTimeZone.UTC)).
                addOperation(new PaymentOperation(RandomStringUtils.random(5), Money.create(Currency.point, 50), Operation.Credit)).
                addOperation(new PaymentOperation(RandomStringUtils.random(5), Money.create(Currency.point, 50), Operation.Debit));
            }
        });
        ObjectGenerator.register(PlayerCredential.class, new AbstractValueGenerator<PlayerCredential>() {
            @Override
            public PlayerCredential generate() {
            return new PlayerCredential(RandomStringUtils.randomAlphabetic(10) + "@gmail.com", RandomStringUtils.random(10));
            }
        });
        ObjectGenerator.register(PlayerProfile.class, new AbstractValueGenerator<PlayerProfile>() {
            @Override
            public PlayerProfile generate() {
            return new PlayerProfile().setBirthDate(new DateTime(0)).setFirstName(RandomStringUtils.randomAlphabetic(10))
                .setGender(PlayerGender.M).setLastName(RandomStringUtils.randomAlphabetic(10)).setNickName(RandomStringUtils.randomAlphabetic(10))
                .setPlayer(RandomStringUtils.random(5)).
                setTimezone("UTC");
            }
        });
        ObjectGenerator.register(ConfigurationRule.class, new AbstractValueGenerator<ConfigurationRule>() {
            @Override
            public ConfigurationRule generate() {
                return UnlimitedBetRule.INSTANCE;
            }
        });
//        ObjectGenerator.register(GameConstruction.class, new AbstractValueGenerator<GameConstruction>() {
//            @Override
//            public GameConstruction generate() {
//            return new AutomaticGameRequest(RoundGameConfiguration.DEFAULT).toConstruction(RandomStringUtils.random(5), RandomStringUtils.random(5));
//            }
//        });
        ObjectGenerator.register(LimitedBetRule.class, new AbstractValueGenerator<LimitedBetRule>() {
            @Override
            public LimitedBetRule generate() {
            return LimitedBetRule.create(10, 200);
            }
        });
//        ObjectGenerator.register(RoundGameConfiguration.class, new AbstractValueGenerator<RoundGameConfiguration>() {
//            @Override
//            public RoundGameConfiguration generate() {
//            return RoundGameConfiguration.DEFAULT;
//            }
//        });
        ObjectGenerator.register(VersionAware.class, "version", new ValueGenerator<Integer>() {
            @Override
            public Integer generate() {
                return 0;
            }

            @Override
            public int scope() {
                return 1;
            }

            public ValueGenerator<Integer> clone() {
                return this;
            }
        });
//        ObjectGenerator.register(GameConfiguration.class, new AbstractValueGenerator<GameConfiguration>() {
//            @Override
//            public GameConfiguration generate() {
//            return RoundGameConfiguration.DEFAULT;
//            }
//        });
//        ObjectGenerator.register(TournamentGameConfiguration.class, new AbstractValueGenerator<TournamentGameConfiguration>() {
//            @Override
//            public TournamentGameConfiguration generate() {
//            return new TournamentGameConfiguration(Game.pic, "AAA", new Money(Currency.point, 50), PlayerNumberRule.two, RoundGameConfiguration.DEFAULT, null, null, null, null, null);
//            }
//        });

        final RSAKeySecret rsaKey = ClembleConsumerDetailUtils.randomKey();
        ObjectGenerator.register(PrivateKey.class, new AbstractValueGenerator<PrivateKey>() {
            @Override
            public PrivateKey generate() {
                return rsaKey.getPrivateKey();
            }
        });
        ObjectGenerator.register(PublicKey.class, new AbstractValueGenerator<PublicKey>() {
            @Override
            public PublicKey generate() {
                return rsaKey.getPublicKey();
            }
        });
//        ObjectGenerator.register(MatchGameContext.class, new AbstractValueGenerator<MatchGameContext>() {
//            @Override
//            public MatchGameContext generate() {
//            return new MatchGameContext(GameSessionAware.DEFAULT_SESSION, null, Collections.<MatchGamePlayerContext>emptyList(), null, 0, Collections.<Outcome>emptyList());
//            }
//        });
//        ObjectGenerator.register(TournamentGameContext.class, new AbstractValueGenerator<TournamentGameContext>() {
//            @Override
//            public TournamentGameContext generate() {
//            return new TournamentGameContext(GameSessionAware.DEFAULT_SESSION, null, null, null);
//            }
//        });
        ObjectGenerator.register(ClembleConsumerDetails.class, new AbstractValueGenerator<ClembleConsumerDetails>() {
            @Override
            public ClembleConsumerDetails generate() {
                return new ClembleConsumerDetails(
                    "consumerKey",
                    "consumer",
                    rsaKey,
                    Collections.<GrantedAuthority>emptyList(),
                    new ClientDetails("consumer")
                );
            }
        });
        try {
            final KeyGenerator AES = KeyGenerator.getInstance("AES");
            AES.init(256, new SecureRandom());
            ObjectGenerator.register(SecretKey.class, new AbstractValueGenerator<SecretKey>() {
                @Override
                public SecretKey generate() {
                    return AES.generateKey();
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
