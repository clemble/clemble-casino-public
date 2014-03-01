package com.clemble.casino.payment.money;

/**
 * Created by mavarazy on 01/03/14.
 */
public class Chip {

    final private Money money;

    public Chip(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

}
