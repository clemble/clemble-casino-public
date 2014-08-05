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
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.payment.service.PaymentTransactionServiceContract;
import com.clemble.casino.utils.CollectionUtils;

public class PaymentTemplate implements PaymentOperations {

    private static final long serialVersionUID = -5498822576528068505L;

    final private String player;
    final private PaymentTransactionServiceContract paymentTransactionService;
    final private EventListenerOperations listenerOperations;

    public PaymentTemplate(
        String player,
        PaymentTransactionServiceContract paymentTransactionService,
        EventListenerOperations listenerOperations) {
        this.player = checkNotNull(player);
        this.paymentTransactionService = checkNotNull(paymentTransactionService);
        this.listenerOperations = checkNotNull(listenerOperations);
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
