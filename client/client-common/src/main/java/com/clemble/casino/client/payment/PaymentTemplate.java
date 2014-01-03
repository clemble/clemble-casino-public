package com.clemble.casino.client.payment;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.money.MoneySource;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.payment.service.PaymentService;

public class PaymentTemplate implements PaymentOperations {

    private static final long serialVersionUID = -5498822576528068505L;

    final private String player;
    final private PaymentService paymentTransactionService;
    final private EventListenerOperations listenerOperations;

    public PaymentTemplate(String player, PaymentService paymentTransactionService, EventListenerOperations listenerOperations) {
        this.player = checkNotNull(player);
        this.paymentTransactionService = checkNotNull(paymentTransactionService);
        this.listenerOperations = checkNotNull(listenerOperations);
    }

    @Override
    public PlayerAccount getAccount() {
        return paymentTransactionService.get(player);
    }

    @Override
    public PaymentTransaction getPaymentTransaction(MoneySource source, String transactionId) {
        return paymentTransactionService.getTransaction(source.name(), transactionId);
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
    public List<PaymentTransaction> getPaymentTransactions(MoneySource source) {
        return getPaymentTransactions(source.name());
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
