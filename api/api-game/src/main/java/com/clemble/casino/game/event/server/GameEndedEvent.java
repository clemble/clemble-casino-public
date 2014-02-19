package com.clemble.casino.game.event.server;

import com.clemble.casino.event.GameEvent;
import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.payment.PaymentTransaction;

public interface GameEndedEvent<GPC extends GamePlayerContext> extends GameEvent {

    public GameContext<GPC> getContext();

    public GameOutcome getOutcome();

    public PaymentTransaction getTransaction();

    public void setTransaction(PaymentTransaction transaction);

}
