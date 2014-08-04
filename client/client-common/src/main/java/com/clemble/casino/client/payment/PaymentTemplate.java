package com.clemble.casino.client.payment;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.service.PaymentTransactionServiceBase;
import com.clemble.casino.payment.service.PlayerAccountService;
import com.clemble.casino.utils.CollectionUtils;

public class PaymentTemplate implements PaymentOperations {

    private static final long serialVersionUID = -5498822576528068505L;

    final private String player;
    final private PlayerAccountService accountService;
    final private PaymentTransactionServiceBase paymentTransactionService;
    final private EventListenerOperations listenerOperations;

    public PaymentTemplate(
        String player,
        PaymentTransactionServiceBase paymentTransactionService,
        PlayerAccountService accountService,
        EventListenerOperations listenerOperations) {
        this.player = checkNotNull(player);
        this.accountService = checkNotNull(accountService);
        this.paymentTransactionService = checkNotNull(paymentTransactionService);
        this.listenerOperations = checkNotNull(listenerOperations);
    }

    @Override
    public PlayerAccount getAccount() {
        return accountService.get(player);
    }

    @Override
    public List<String> canAfford(Collection<String> players, Currency currency, long amount) {
        return accountService.canAfford(players, currency, amount);
    }

    public PaymentTransaction getPaymentTransaction(GameSessionKey sessionKey) {
        return getPaymentTransaction(sessionKey.toPaymentTransactionKey());
    }

    @Override
    public PaymentTransaction getPaymentTransaction(Game game, String transaction) {
        return paymentTransactionService.getTransaction(game.name(), transaction);
    }

    @Override
    public PaymentTransaction getPaymentTransaction(PaymentTransactionKey transactionKey) {
        return getPaymentTransaction(transactionKey.getSource(), transactionKey.getTransaction());
    }

    @Override
    public PaymentTransaction getPaymentTransaction(String source, String transactionId) {
        return paymentTransactionService.getTransaction(source, transactionId);
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactionService.getPlayerTransactions(player);
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactions(String source) {
        return paymentTransactionService.getPlayerTransactionsWithSource(player, source);
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactions(PaymentBonusSource source) {
        return getPaymentTransactions(source.name());
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactions(Game game) {
        if (game == null)
            return CollectionUtils.<PaymentTransaction> immutableList();
        return getPaymentTransactions(game.name());
    }

    @Override
    public EventListenerController subscribe(EventListener<PaymentEvent> listener) {
        return listenerOperations.subscribe(new PaymentEventSelector(), listener);
    }

    @Override
    public String getPlayer() {
        return player;
    }

}
