package com.clemble.casino.json;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.clemble.casino.game.lifecycle.construction.event.PlayerInvitationAcceptedAction;
import com.clemble.casino.game.lifecycle.management.*;
import com.clemble.casino.goal.lifecycle.management.GoalContext;
import com.clemble.casino.goal.lifecycle.management.GoalPlayerContext;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.GiveUpAction;
import com.clemble.casino.game.*;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.game.lifecycle.management.unit.Chip;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.clemble.casino.payment.PendingOperation;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.oauth.common.signature.RSAKeySecret;

import com.clemble.casino.VersionAware;
import com.clemble.casino.game.lifecycle.construction.AutomaticGameRequest;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.management.outcome.GameOutcome;
import com.clemble.casino.lifecycle.configuration.rule.bet.FixedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.LimitedBetRule;
import com.clemble.casino.lifecycle.configuration.rule.bet.UnlimitedBetRule;
import com.clemble.casino.game.lifecycle.configuration.rule.construct.PlayerNumberRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.TournamentGameConfiguration;
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
        ObjectGenerator.register(TournamentGameState.class, new AbstractValueGenerator<TournamentGameState>() {
            @Override
            public TournamentGameState generate() {
                TournamentGameContext context = new TournamentGameContext(
                        "",
                        null,
                        ObjectGenerator.generateList(TournamentGamePlayerContext.class, 10),
                        null);
                return new TournamentGameState(
                        ObjectGenerator.generate(TournamentGameConfiguration.class),
                        context,
                        null,
                        0);
            }
        });
        ObjectGenerator.register(SortedSet.class, new AbstractValueGenerator<SortedSet>() {
            @Override
            public SortedSet generate() {
                return new TreeSet();
            }
        });
        ObjectGenerator.register(GameUnit.class, new AbstractValueGenerator<GameUnit>() {
            @Override
            public GameUnit generate() {
                return Chip.zero;
            }
        });
        ObjectGenerator.register(PlayerExpectedAction.class, new AbstractValueGenerator<PlayerExpectedAction>() {
            @Override
            public PlayerExpectedAction generate() {
            return PlayerExpectedAction.fromClass(PlayerInvitationAcceptedAction.class);
            }
        });
        ObjectGenerator.register(GoalContext.class, new AbstractValueGenerator<GoalContext>() {
            @Override
            public GoalContext generate() {
                List<GoalPlayerContext> playerContexts = Collections.emptyList();
                return new GoalContext(null, playerContexts);
            }
        });
        ObjectGenerator.register(RoundGameContext.class, new AbstractValueGenerator<RoundGameContext>() {
            @Override
            public RoundGameContext generate() {
            GameInitiation initiation = new GameInitiation(GameSessionAware.DEFAULT_SESSION, InitiationState.pending, ImmutableList.of("A", "B"), RoundGameConfiguration.DEFAULT);
            return RoundGameContext.fromInitiation(initiation, null);
            }

        });
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
                ImmutableMap.of(Currency.FakeMoney, Money.create(Currency.FakeMoney, 500)),
                Collections.<PendingOperation>emptyList(),
                null);
            }
        });
        ObjectGenerator.register(PaymentTransaction.class, new AbstractValueGenerator<PaymentTransaction>() {
            @Override
            public PaymentTransaction generate() {
            return new PaymentTransaction()
                    .setTransactionKey(RandomStringUtils.random(5))
                    .setTransactionDate(new Date())
                    .setProcessingDate(new Date())
                    .addOperation(
                            new PaymentOperation(RandomStringUtils.random(5), Money.create(Currency.FakeMoney, 50), Operation.Credit))
                    .addOperation(
                            new PaymentOperation(RandomStringUtils.random(5), Money.create(Currency.FakeMoney, 50), Operation.Debit));
            }
        });
        ObjectGenerator.register(PlayerCredential.class, new AbstractValueGenerator<PlayerCredential>() {
            @Override
            public PlayerCredential generate() {
            return new PlayerCredential().setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com").setPassword(RandomStringUtils.random(10))
                        .setPlayer(RandomStringUtils.random(5));
            }
        });
        ObjectGenerator.register(PlayerProfile.class, new AbstractValueGenerator<PlayerProfile>() {
            @Override
            public PlayerProfile generate() {
            return new PlayerProfile().setBirthDate(new Date(0)).setFirstName(RandomStringUtils.randomAlphabetic(10))
                .setGender(PlayerGender.M).setLastName(RandomStringUtils.randomAlphabetic(10)).setNickName(RandomStringUtils.randomAlphabetic(10))
                .setPlayer(RandomStringUtils.random(5));
            }
        });
        ObjectGenerator.register(ConfigurationRule.class, new AbstractValueGenerator<ConfigurationRule>() {
            @Override
            public ConfigurationRule generate() {
                return UnlimitedBetRule.INSTANCE;
            }
        });
        ObjectGenerator.register(GameConstruction.class, new AbstractValueGenerator<GameConstruction>() {
            @Override
            public GameConstruction generate() {
            return new AutomaticGameRequest(RoundGameConfiguration.DEFAULT).toConstruction(RandomStringUtils.random(5), RandomStringUtils.random(5));
            }
        });
        ObjectGenerator.register(LimitedBetRule.class, new AbstractValueGenerator<LimitedBetRule>() {
            @Override
            public LimitedBetRule generate() {
            return LimitedBetRule.create(10, 200);
            }
        });
        ObjectGenerator.register(RoundGameConfiguration.class, new AbstractValueGenerator<RoundGameConfiguration>() {
            @Override
            public RoundGameConfiguration generate() {
            return RoundGameConfiguration.DEFAULT;
            }
        });
        ObjectGenerator.register(PrivacyRule.class, new AbstractValueGenerator<PrivacyRule>() {
            @Override
            public PrivacyRule generate() {
                return PrivacyRule.everybody;
            }
        });
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
        ObjectGenerator.register(GameConfiguration.class, new AbstractValueGenerator<GameConfiguration>() {
            @Override
            public GameConfiguration generate() {
            return RoundGameConfiguration.DEFAULT;
            }
        });
        ObjectGenerator.register(TournamentGameConfiguration.class, new AbstractValueGenerator<TournamentGameConfiguration>() {
            @Override
            public TournamentGameConfiguration generate() {
            return new TournamentGameConfiguration(Game.pic, "AAA", new Money(Currency.FakeMoney, 50), PrivacyRule.players, PlayerNumberRule.two, RoundGameConfiguration.DEFAULT, null, null, null, null, null);
            }
        });

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
        ObjectGenerator.register(MatchGameContext.class, new AbstractValueGenerator<MatchGameContext>() {
            @Override
            public MatchGameContext generate() {
            return new MatchGameContext(GameSessionAware.DEFAULT_SESSION, null, Collections.<MatchGamePlayerContext>emptyList(), null, 0, Collections.<GameOutcome>emptyList());
            }
        });
        ObjectGenerator.register(TournamentGameContext.class, new AbstractValueGenerator<TournamentGameContext>() {
            @Override
            public TournamentGameContext generate() {
            return new TournamentGameContext(GameSessionAware.DEFAULT_SESSION, null, null, null);
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
